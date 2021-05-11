package com.dwalter.bookingsystem.user.userRegistration.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
