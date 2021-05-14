package com.dwalter.bookingsystem.reservation.domain;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class ReservationDto {
    private Long id;
    private LocalDateTime placingDate;
    private LocalDateTime reservationFrom;
    private LocalDateTime reservationTo;
    private boolean paid;
}
