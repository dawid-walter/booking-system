package com.dwalter.bookingsystem.functionality.room.model;

import com.dwalter.bookingsystem.functionality.comment.model.Comment;
import com.dwalter.bookingsystem.functionality.reservation.model.Reservation;
import com.dwalter.bookingsystem.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Room extends BaseEntity {
    private int capacity;
    @Enumerated(EnumType.STRING)
    private Color color;
    private String title;
    private String description;
    private BigDecimal pricePerDay;
    private String imageUrl;
    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private Set<Reservation> reservations;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "room_id", updatable = false, insertable = false)
    private List<Comment> comments;

    @Transient
    public boolean isRoomDateMatched(LocalDate from, LocalDate to) {
        return reservations.stream()
                .anyMatch(reservation -> from.isBefore(reservation.getReservationTo()) && to.isAfter(reservation.getReservationFrom()));
    }
}
