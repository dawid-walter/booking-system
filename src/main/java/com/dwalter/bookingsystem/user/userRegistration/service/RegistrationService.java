package com.dwalter.bookingsystem.user.userRegistration.service;

import com.dwalter.bookingsystem.user.userRegistration.domain.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    public String register(RegistrationRequest request) {
        return "Username " + request.getFirstName()+ " successfully registered";
    }
}
