package com.dwalter.bookingsystem.user.userRegistration.controller;

import com.dwalter.bookingsystem.user.userRegistration.domain.RegistrationRequest;
import com.dwalter.bookingsystem.user.userRegistration.service.RegistrationService;
import com.dwalter.bookingsystem.user.userRegistration.token.domain.AuthenticationToken;
import com.dwalter.bookingsystem.user.userRegistration.token.service.AuthenticationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final AuthenticationTokenService authenticationTokenService;

    @PostMapping
    public AuthenticationToken register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping
    public String confirm(@RequestParam("token") String token) {
        return authenticationTokenService.confirmToken(token);
    }
}
