package com.dwalter.bookingsystem.functionality.reservation.repository;

import com.dwalter.bookingsystem.functionality.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
