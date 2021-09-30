package com.dwalter.bookingsystem.functionality.reservation.service;

import com.dwalter.bookingsystem.functionality.reservation.model.Reservation;
import com.dwalter.bookingsystem.functionality.reservation.controller.dto.ReservationDto;
import com.dwalter.bookingsystem.functionality.reservation.controller.dto.ReservationRequest;
import com.dwalter.bookingsystem.functionality.reservation.mapper.ReservationMapper;
import com.dwalter.bookingsystem.functionality.reservation.repository.ReservationRepository;
import com.dwalter.bookingsystem.functionality.reservation.exceptions.ReservationNotFoundByIdException;
import com.dwalter.bookingsystem.functionality.room.model.Room;
import com.dwalter.bookingsystem.functionality.room.mapper.RoomMapper;
import com.dwalter.bookingsystem.functionality.room.service.RoomDbService;
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
    public Reservation create(ReservationRequest reservationRequest) {
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

        Reservation savedReservation = reservationRepository.save(reservation);
        List<Reservation> roomReservations = room.getReservations();
        roomReservations.add(reservation);
        room.setReservations(roomReservations);
        roomDbService.update(roomMapper.mapToRoomDto(room));
        return savedReservation;
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

    public void deleteAll() {
        reservationRepository.deleteAll();
    }
}
