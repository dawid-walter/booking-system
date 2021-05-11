package com.dwalter.bookingsystem.user.userRegistration.token.service;

import com.dwalter.bookingsystem.user.domain.User;
import com.dwalter.bookingsystem.user.service.UserDbService;
import com.dwalter.bookingsystem.user.userRegistration.token.domain.ConfirmationToken;
import com.dwalter.bookingsystem.user.userRegistration.token.repository.ConfirmationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserDbService userDbService;

    public void saveToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    private Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    @Transactional
    public String confirmToken(String token) {

        ConfirmationToken confirmationToken = getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("Token not found"));

        if (confirmationToken.getConfirmed() != null) {
            throw new IllegalStateException("Email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiring();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expired");
        }

        confirmationToken.setConfirmed(LocalDateTime.now());
        User byUsername = userDbService.findByUsername(confirmationToken.getUser().getUsername())
                .orElseThrow(() ->
                        new IllegalStateException("User not found"));
        byUsername.setEnabled(true);

        return "Token confirmed";
    }
}

