package com.dwalter.bookingsystem.reservation.controller;

import com.dwalter.bookingsystem.reservation.domain.ReservationDto;
import com.dwalter.bookingsystem.reservation.exceptions.ReservationNotFoundByIdException;
import com.dwalter.bookingsystem.reservation.mapper.ReservationMapper;
import com.dwalter.bookingsystem.reservation.service.ReservationDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationDbService reservationDbService;
    private final ReservationMapper reservationMapper;

    @GetMapping
    public ResponseEntity<List<ReservationDto>> get() {
        log.info("Get reservations!");
        List<ReservationDto> reservations = reservationMapper.mapToReservationsDto(reservationDbService.getAll());
        return new ResponseEntity<>(reservations, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> get(@PathVariable final Long id) {
        log.info("Get reservation by id: " + id);
        ReservationDto reservation = reservationMapper.mapToReservationDto(reservationDbService.getById(id)
                .orElseThrow(() -> new ReservationNotFoundByIdException(id)));
        return new ResponseEntity<>(reservation, OK);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDto> create(@RequestBody final ReservationDto reservationDto) {
        ReservationDto reservation = reservationMapper.mapToReservationDto(reservationDbService.create(reservationMapper.mapToReservation(reservationDto)));
        return new ResponseEntity<>(reservation, CREATED);
    }

    @PutMapping
    public ResponseEntity<ReservationDto> update(@RequestBody final ReservationDto reservationDto) {
        ReservationDto updateReservation = reservationDbService.update(reservationDto);
        return new ResponseEntity<>(reservationDto, OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final Long id) {
        reservationDbService.delete(id);
        return new ResponseEntity<>("Reservation Deleted Successfully!", OK);
    }
}
