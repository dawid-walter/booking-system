package com.dwalter.bookingsystem.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ReservationDto {
    private final Long id;
    private LocalDateTime placingDate;
    private final LocalDateTime reservationFrom;
    private final LocalDateTime reservationTo;
    private boolean paid;
}
