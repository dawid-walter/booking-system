package com.dwalter.bookingsystem.user.service;

import com.dwalter.bookingsystem.util.Constants;
import com.dwalter.bookingsystem.user.domain.User;
import com.dwalter.bookingsystem.user.userRegistration.token.domain.ConfirmationToken;
import com.dwalter.bookingsystem.user.userRegistration.token.service.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private static final String USER_NOT_FOUND = "User not found";
    private static final String EMAIL_TAKEN = "Email already exist";
    private final UserDbService userDbService;
    private final ConfirmationTokenService confirmationTokenService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDbService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
    }

    public ConfirmationToken register(User user) {
        boolean userExists = userDbService
                .findByUsername(user.getUsername())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException(EMAIL_TAKEN);
        }

        userDbService.save(user);

        ConfirmationToken token = ConfirmationToken.builder()
                .token(UUID.randomUUID().toString())
                .created(LocalDateTime.now())
                .expiring(LocalDateTime.now().plusMinutes(Constants.REGISTRATION_EMAIL_EXPIRATION_TIME))
                .user(user)
                .build();

        confirmationTokenService.saveToken(token);
        log.info("Token created " + token.getToken());
        return token;
    }
}
