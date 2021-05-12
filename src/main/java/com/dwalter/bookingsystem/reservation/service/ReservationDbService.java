package com.dwalter.bookingsystem.reservation.service;

import com.dwalter.bookingsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationDbService {
    private final ReservationRepository reservationRepository;
}
