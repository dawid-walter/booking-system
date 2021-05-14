package com.dwalter.bookingsystem.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private UserRole userRole;
    private boolean locked;
    private boolean enabled;
}
