package com.dwalter.bookingsystem.reservation.service;

import com.dwalter.bookingsystem.reservation.domain.Reservation;
import com.dwalter.bookingsystem.reservation.domain.ReservationDto;
import com.dwalter.bookingsystem.reservation.domain.ReservationRequest;
import com.dwalter.bookingsystem.reservation.exceptions.ReservationNotFoundByIdException;
import com.dwalter.bookingsystem.reservation.mapper.ReservationMapper;
import com.dwalter.bookingsystem.reservation.repository.ReservationRepository;
import com.dwalter.bookingsystem.room.domain.Room;
import com.dwalter.bookingsystem.room.exceptions.RoomNotFoundByIdException;
import com.dwalter.bookingsystem.room.mapper.RoomMapper;
import com.dwalter.bookingsystem.room.service.RoomDbService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationDbService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final RoomDbService roomDbService;
    private final RoomMapper roomMapper;

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getById(Long id) {
        return reservationRepository.findById(id);
    }


    @Transactional
    public ReservationRequest create(ReservationRequest reservationRequest) {
        LocalDate timeNow = LocalDate.now();
        log.info("Create Reservation: " +
                "Room_Id - " + reservationRequest.getRoomId() +
                ", Start_Date - " + reservationRequest.getReservationFrom() +
                ", End_date - " + reservationRequest.getReservationTo() +
                " created at: " + timeNow);

        Room room = roomDbService.getById(reservationRequest.getRoomId()).orElseThrow(() -> new ReservationNotFoundByIdException(reservationRequest.getRoomId()));

        Reservation reservation = Reservation.builder()
                .placingDate(timeNow)
                .reservationFrom(reservationRequest.getReservationFrom())
                .reservationTo(reservationRequest.getReservationTo())
                .room(room)
                .build();

        reservationRepository.save(reservation);
        List<Reservation> roomReservations = room.getReservations();
        roomReservations.add(reservation);
        room.setReservations(roomReservations);
        roomDbService.update(roomMapper.mapToRoomDto(room));
        return reservationRequest;
    }

    @Transactional
    public Reservation update(ReservationDto reservationDto) {
        Reservation reservation = reservationRepository.findById(reservationDto.getId())
                .orElseThrow(() -> new ReservationNotFoundByIdException(reservationDto.getId()));
        reservation.setReservationFrom(reservationDto.getReservationFrom());
        reservation.setReservationTo(reservationDto.getReservationTo());
        return reservation;
    }

    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> getReservationsByRoomId(Long id) {
        return getAll().stream()
                .filter(reservation -> reservation.getRoom().getId().equals(id))
                .collect(Collectors.toList());
    }
}
