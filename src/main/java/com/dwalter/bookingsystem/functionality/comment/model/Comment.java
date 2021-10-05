package com.dwalter.bookingsystem.functionality.comment.model;

import com.dwalter.bookingsystem.functionality.room.model.Room;
import com.dwalter.bookingsystem.model.BaseEntity;
import com.dwalter.bookingsystem.user.model.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment extends BaseEntity {
    private String content;
    private LocalDateTime created;
    @Column(name = "room_id")
    private Long roomId;
}
