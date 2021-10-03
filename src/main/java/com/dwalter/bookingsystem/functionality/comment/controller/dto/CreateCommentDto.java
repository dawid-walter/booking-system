package com.dwalter.bookingsystem.functionality.comment.controller.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class CreateCommentDto {
    private String content;
    private Long roomId;
}
