package com.dwalter.bookingsystem.functionality.reservation.exceptions;

public class ReservationNotFoundByIdException extends RuntimeException {
    public ReservationNotFoundByIdException(final Long id) {
        super("Could not find reservation by id: " + id);
    }
}
