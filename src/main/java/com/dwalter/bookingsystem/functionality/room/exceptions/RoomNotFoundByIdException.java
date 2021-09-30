package com.dwalter.bookingsystem.functionality.room.exceptions;

public final class RoomNotFoundByIdException extends RuntimeException {
    public RoomNotFoundByIdException(final Long id) {
        super("Could not find room by id: " + id);
    }
}


