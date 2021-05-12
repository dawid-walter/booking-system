package com.dwalter.bookingsystem.user.userRegistration.token.service;

import com.dwalter.bookingsystem.user.domain.User;
import com.dwalter.bookingsystem.user.service.UserDbService;
import com.dwalter.bookingsystem.user.userRegistration.domain.RegistrationRequest;
import com.dwalter.bookingsystem.user.userRegistration.service.RegistrationService;
import com.dwalter.bookingsystem.user.userRegistration.token.domain.AuthenticationToken;
import com.dwalter.bookingsystem.user.userRegistration.token.repository.AuthenticationTokenRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@Disabled
@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {
    @InjectMocks
    RegistrationService registrationService;
    @Mock
    UserDbService userDbService;
    @Mock
    AuthenticationTokenService authenticationTokenService;


    @Test
    void should_confirm_email_registration_token() {
        //Given
        RegistrationRequest request = new RegistrationRequest("test", "test", "test", "test");
        //When
        AuthenticationToken registerToken = registrationService.register(request);

        //Then
    }
}
