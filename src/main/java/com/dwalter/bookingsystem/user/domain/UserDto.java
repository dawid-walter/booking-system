package com.dwalter.bookingsystem.user.domain;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private UserRole userRole;
    private boolean locked;
    private boolean enabled;
}
