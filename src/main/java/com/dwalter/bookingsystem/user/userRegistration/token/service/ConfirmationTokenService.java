package com.dwalter.bookingsystem.user.userRegistration.token.service;

import com.dwalter.bookingsystem.user.userRegistration.token.domain.ConfirmationToken;
import com.dwalter.bookingsystem.user.userRegistration.token.repository.ConfirmationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenService  {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }
}
