package com.dwalter.bookingsystem.security.userRegistration.controller;

import com.dwalter.bookingsystem.security.userRegistration.model.LoginCredentials;
import com.dwalter.bookingsystem.security.userRegistration.service.RegistrationService;
import com.dwalter.bookingsystem.security.userRegistration.token.model.AuthenticationToken;
import com.dwalter.bookingsystem.security.userRegistration.token.service.AuthenticationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final AuthenticationTokenService authenticationTokenService;

    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials request) {
    }

    @PostMapping
    public AuthenticationToken register(@RequestBody LoginCredentials request) {
        return registrationService.register(request);
    }

    @GetMapping
    public String confirm(@RequestParam("token") String token) {
        return authenticationTokenService.confirmToken(token);
    }
}

