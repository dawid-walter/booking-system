package com.dwalter.bookingsystem.user.userRegistration.token.domain;

import com.dwalter.bookingsystem.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@RequiredArgsConstructor
@Entity
public class ConfirmationToken {
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
