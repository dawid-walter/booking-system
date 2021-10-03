package com.dwalter.bookingsystem.functionality.reservation.mapper;

import com.dwalter.bookingsystem.functionality.reservation.controller.dto.ReservationDto;
import com.dwalter.bookingsystem.functionality.reservation.model.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {
    private ReservationMapper() {
    }

    public static Reservation mapToReservation(ReservationDto reservationDto) {
        return Reservation.builder()
                .id(reservationDto.getId())
                .reservationFrom(reservationDto.getReservationFrom())
                .reservationTo(reservationDto.getReservationTo())
                .placingDate(reservationDto.getPlacingDate())
                .paid(reservationDto.isPaid())
                .build();
    }

    public static ReservationDto mapToReservationDto(Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .reservationFrom(reservation.getReservationFrom())
                .reservationTo(reservation.getReservationTo())
                .placingDate(reservation.getPlacingDate())
                .paid(reservation.isPaid())
                .roomId(reservation.getRoom().getId())
                .build();
    }

    public static List<ReservationDto> mapToReservationsDto(List<Reservation> reservations) {
        return reservations.stream()
                .map(ReservationMapper::mapToReservationDto)
                .collect(Collectors.toList());
    }
}
