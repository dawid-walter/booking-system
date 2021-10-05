package com.dwalter.bookingsystem.functionality.reservation.model;

import com.dwalter.bookingsystem.functionality.room.model.Room;
import com.dwalter.bookingsystem.functionality.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Reservation extends BaseEntity {
    private LocalDate placingDate;
    private LocalDate reservationFrom;
    private LocalDate reservationTo;
    private boolean paid = false;
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;
}
