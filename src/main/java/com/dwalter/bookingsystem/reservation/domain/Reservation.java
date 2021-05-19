package com.dwalter.bookingsystem.reservation.domain;

import com.dwalter.bookingsystem.room.domain.Room;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate placingDate;
    private LocalDate reservationFrom;
    private LocalDate reservationTo;
    private boolean paid = false;
    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;
}
