package com.dwalter.bookingsystem.user.userRegistration.token.domain;

import com.dwalter.bookingsystem.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime created;
    private LocalDateTime expiring;
    private LocalDateTime confirmed;
    @ManyToOne
    private User user;
}
