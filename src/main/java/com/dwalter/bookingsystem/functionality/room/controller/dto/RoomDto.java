package com.dwalter.bookingsystem.functionality.room.controller.dto;

import com.dwalter.bookingsystem.functionality.comment.controller.dto.CommentDto;
import com.dwalter.bookingsystem.functionality.reservation.controller.dto.ReservationDto;
import com.dwalter.bookingsystem.functionality.room.model.Color;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class RoomDto {
    private final Long id;
    private final int capacity;
    private final Color color;
    private final String title;
    private final String description;
    private final BigDecimal pricePerDay;
    private final String imageUrl;
    private final List<ReservationDto> reservations;
    private final List<CommentDto> comments;
}
