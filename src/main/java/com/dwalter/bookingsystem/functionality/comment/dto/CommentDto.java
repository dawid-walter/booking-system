package com.dwalter.bookingsystem.functionality.comment.dto;

import com.dwalter.bookingsystem.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class CommentDto {
    private Long id;
    private LocalDateTime created;
    private String content;
    private User author;
}
