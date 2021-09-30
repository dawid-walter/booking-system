package com.dwalter.bookingsystem.functionality.room.dto;

import com.dwalter.bookingsystem.functionality.reservation.dto.ReservationDto;
import com.dwalter.bookingsystem.functionality.room.entity.Color;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class RoomDto {
    private Long id;
    private int capacity;
    private Color color;
    private String title;
    private String description;
    private BigDecimal pricePerDay;
    private String imageUrl;
    private List<ReservationDto> reservations;
}