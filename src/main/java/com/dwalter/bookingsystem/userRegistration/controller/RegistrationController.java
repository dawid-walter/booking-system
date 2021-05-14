package com.dwalter.bookingsystem.userRegistration.controller;

import com.dwalter.bookingsystem.userRegistration.domain.RegistrationRequest;
import com.dwalter.bookingsystem.userRegistration.service.RegistrationService;
import com.dwalter.bookingsystem.userRegistration.token.domain.AuthenticationToken;
import com.dwalter.bookingsystem.userRegistration.token.service.AuthenticationTokenService;
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
