package com.dwalter.bookingsystem.functionality.comment.controller.dto;

import com.dwalter.bookingsystem.user.model.User;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class CommentDto {
    private Long id;
    private String content;
    private LocalDateTime created;
}
