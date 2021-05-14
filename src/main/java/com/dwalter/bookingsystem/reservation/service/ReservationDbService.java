package com.dwalter.bookingsystem.reservation.service;

import com.dwalter.bookingsystem.reservation.domain.Reservation;
import com.dwalter.bookingsystem.reservation.domain.ReservationDto;
import com.dwalter.bookingsystem.reservation.exceptions.ReservationNotFoundByIdException;
import com.dwalter.bookingsystem.reservation.mapper.ReservationMapper;
import com.dwalter.bookingsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationDbService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation create(Reservation reservation) {
        reservation.setPlacingDate(LocalDateTime.now());
        return reservationRepository.save(reservation);
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
}
