package com.dwalter.bookingsystem.user.service;

import com.dwalter.bookingsystem.user.domain.User;
import com.dwalter.bookingsystem.user.repository.UserRepository;
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
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
    }

    public void signUp(User user) {
        boolean userExists = userRepository
                .findByUsername(user.getUsername())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException(EMAIL_TAKEN);
        }

        save(user);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
