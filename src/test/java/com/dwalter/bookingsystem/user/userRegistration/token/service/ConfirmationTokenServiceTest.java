package com.dwalter.bookingsystem.user.userRegistration.token.service;

import com.dwalter.bookingsystem.user.domain.User;
import com.dwalter.bookingsystem.user.service.UserDbService;
import com.dwalter.bookingsystem.user.userRegistration.domain.RegistrationRequest;
import com.dwalter.bookingsystem.user.userRegistration.service.RegistrationService;
import com.dwalter.bookingsystem.user.userRegistration.token.domain.ConfirmationToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ConfirmationTokenServiceTest {
    @Autowired
    UserDbService userDbService;
    @Autowired
    ConfirmationTokenService confirmationTokenService;
    @Autowired
    RegistrationService registrationService;

    @Test
    void should_confirm_email_registration_token() {
        //Given
        RegistrationRequest request = new RegistrationRequest("test", "test", "test", "test");
        //When
        ConfirmationToken registerToken = registrationService.register(request);
        confirmationTokenService.confirmToken(registerToken.getToken());
        User byToken = userDbService.findByToken(registerToken.getToken());
        //Then
        assertThat(byToken.isEnabled()).isTrue();
    }
}
