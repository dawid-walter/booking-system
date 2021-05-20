package com.dwalter.bookingsystem.room.domain;

import com.dwalter.bookingsystem.reservation.domain.Reservation;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int capacity;
    @Enumerated(EnumType.STRING)
    private Color color;
    private String title;
    private String description;
    private BigDecimal pricePerDay;
    private String imageUrl;
    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    public boolean isRoomDateMatched(LocalDate from, LocalDate to) {
        return reservations.stream()
                .anyMatch(reservation -> from.isBefore(reservation.getReservationTo()) && to.isAfter(reservation.getReservationFrom()));
    }
}
