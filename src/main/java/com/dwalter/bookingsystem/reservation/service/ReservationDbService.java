package com.dwalter.bookingsystem.reservation.service;

import com.dwalter.bookingsystem.reservation.domain.Reservation;
import com.dwalter.bookingsystem.reservation.domain.ReservationDto;
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
    public ReservationDto create(ReservationDto reservationDto) {
        log.info("Create Reservation_" +
                "Room_Id - " + reservationDto.getRoom().getId() +
                ", Start_Date - " + reservationDto.getReservationFrom() +
                ", End_date - " + reservationDto.getReservationTo());
        Room room = roomDbService.getById(reservationDto.getRoom().getId()).orElseThrow(() -> new ReservationNotFoundByIdException(reservationDto.getRoom().getId()));
        Reservation reservation = reservationMapper.mapToReservation(reservationDto);
        List<Reservation> roomReservations = room.getReservations();
        roomReservations.add(reservation);
        room.setReservations(roomReservations);
        roomDbService.update(roomMapper.mapToRoomDto(room));
        reservationRepository.save(reservation);
        return reservationDto;
    }

    @Transactional
    public ReservationDto update(ReservationDto reservationDto) {
        Reservation reservation = reservationRepository.findById(reservationDto.getId())
                .orElseThrow(() -> new ReservationNotFoundByIdException(reservationDto.getId()));
        reservation.setReservationFrom(reservationDto.getReservationFrom());
        reservation.setReservationTo(reservationDto.getReservationTo());
        return reservationMapper.mapToReservationDto(reservation);
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
