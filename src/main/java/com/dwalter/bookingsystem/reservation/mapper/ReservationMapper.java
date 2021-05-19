package com.dwalter.bookingsystem.reservation.mapper;

import com.dwalter.bookingsystem.reservation.domain.Reservation;
import com.dwalter.bookingsystem.reservation.domain.ReservationDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationDto mapToReservationDto(Reservation reservation);

    Reservation mapToReservation(ReservationDto reservationDto);

    List<ReservationDto> mapToReservationsDto(List<Reservation> reservations);

    List<Reservation> mapToReservations(List<ReservationDto> reservationDtos);
}
