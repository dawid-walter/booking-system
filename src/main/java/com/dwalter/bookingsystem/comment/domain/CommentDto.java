package com.dwalter.bookingsystem.comment.domain;

import com.dwalter.bookingsystem.user.domain.User;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class CommentDto {
    private LocalDateTime created;
    private String content;
    private User author;
}
