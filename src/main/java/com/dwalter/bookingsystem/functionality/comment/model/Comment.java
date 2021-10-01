package com.dwalter.bookingsystem.functionality.comment.model;

import com.dwalter.bookingsystem.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime created;
    private String content;
    @OneToOne
    private User author;
}
