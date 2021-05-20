package com.dwalter.bookingsystem.user.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(final Long id) {
        super("Could not find user by id: " + id);
    }
}
