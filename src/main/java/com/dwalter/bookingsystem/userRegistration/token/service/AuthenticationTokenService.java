package com.dwalter.bookingsystem.userRegistration.token.service;

import com.dwalter.bookingsystem.user.model.User;
import com.dwalter.bookingsystem.user.service.UserDbService;
import com.dwalter.bookingsystem.userRegistration.token.model.AuthenticationToken;
import com.dwalter.bookingsystem.userRegistration.token.repository.AuthenticationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationTokenService {
    private final AuthenticationTokenRepository authenticationTokenRepository;
    private final UserDbService userDbService;

    public void saveToken(AuthenticationToken token) {
        authenticationTokenRepository.save(token);
    }

    private Optional<AuthenticationToken> getToken(String token) {
        return authenticationTokenRepository.findByToken(token);
    }

    @Transactional
    public String confirmToken(String token) {

        AuthenticationToken authenticationToken = getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("Token not found"));

        if (authenticationToken.getConfirmed() != null) {
            throw new IllegalStateException("Email already confirmed");
        }

        LocalDateTime expiredAt = authenticationToken.getExpiring();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expired");
        }

        authenticationToken.setConfirmed(LocalDateTime.now());
        User byUsername = userDbService.findByUsername(authenticationToken.getUser().getUsername())
                .orElseThrow(() ->
                        new IllegalStateException("User not found"));
        byUsername.setEnabled(true);

        return "Token confirmed";
    }
}

