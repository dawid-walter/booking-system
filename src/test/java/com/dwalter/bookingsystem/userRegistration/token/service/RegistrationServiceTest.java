package com.dwalter.bookingsystem.userRegistration.token.service;

import com.dwalter.bookingsystem.user.service.UserDbService;
import com.dwalter.bookingsystem.userRegistration.domain.RegistrationRequest;
import com.dwalter.bookingsystem.userRegistration.service.RegistrationService;
import com.dwalter.bookingsystem.userRegistration.token.domain.AuthenticationToken;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
