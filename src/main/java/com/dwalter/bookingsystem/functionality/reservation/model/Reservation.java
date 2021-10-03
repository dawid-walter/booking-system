package com.dwalter.bookingsystem.functionality.reservation.model;

import com.dwalter.bookingsystem.functionality.room.model.Room;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate placingDate;
    private LocalDate reservationFrom;
    private LocalDate reservationTo;
    private boolean paid = false;
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;
}
