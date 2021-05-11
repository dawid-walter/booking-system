package com.dwalter.bookingsystem.user.service;

import com.dwalter.bookingsystem.user.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private static final String USER_NOT_FOUND = "User not found";
    private static final String EMAIL_TAKEN = "Email already exist";
    private final UserDbService userDbService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDbService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
    }

    public void signUp(User user) {
        boolean userExists = userDbService
                .findByUsername(user.getUsername())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException(EMAIL_TAKEN);
        }

        userDbService.save(user);
    }

}
