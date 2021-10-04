package com.dwalter.bookingsystem.functionality.room.controller.dto;

import com.dwalter.bookingsystem.functionality.room.model.Color;
import lombok.*;

import java.math.BigDecimal;

@Builder
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class UpdateRoomRequest {
    private final Long id;
    private final int capacity;
    private final Color color;
    private final String title;
    private final String description;
    private final BigDecimal pricePerDay;
    private final String imageUrl;
}
