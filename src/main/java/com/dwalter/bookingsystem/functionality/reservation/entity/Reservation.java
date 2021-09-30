package com.dwalter.bookingsystem.functionality.reservation.entity;

import com.dwalter.bookingsystem.functionality.room.entity.Room;
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
