package com.dwalter.bookingsystem.user.userRegistration.service;

import com.dwalter.bookingsystem.service.EmailValidator;
import com.dwalter.bookingsystem.user.domain.User;
import com.dwalter.bookingsystem.user.domain.UserRole;
import com.dwalter.bookingsystem.user.service.UserService;
import com.dwalter.bookingsystem.user.userRegistration.domain.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private static final String EMAIL_NOT_VALID = "Email not valid";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException(EMAIL_NOT_VALID);
        }

        userService.signUp(User.builder()
                .username(request.getFirstName())
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .userRole(UserRole.USER)
                .build());

        return "Username " + request.getFirstName() + " successfully registered";
    }
}
