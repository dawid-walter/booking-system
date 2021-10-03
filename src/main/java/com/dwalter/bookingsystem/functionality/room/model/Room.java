package com.dwalter.bookingsystem.functionality.room.model;

import com.dwalter.bookingsystem.functionality.comment.model.Comment;
import com.dwalter.bookingsystem.functionality.reservation.model.Reservation;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
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
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private Set<Reservation> reservations;
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private Set<Comment> comments;

    @Transient
    public boolean isRoomDateMatched(LocalDate from, LocalDate to) {
        return reservations.stream()
                .anyMatch(reservation -> from.isBefore(reservation.getReservationTo()) && to.isAfter(reservation.getReservationFrom()));
    }
}
