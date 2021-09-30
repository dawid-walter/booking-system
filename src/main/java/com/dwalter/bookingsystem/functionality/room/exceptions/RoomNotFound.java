package com.dwalter.bookingsystem.functionality.room.exceptions;

public class RoomNotFound extends RuntimeException {
    public RoomNotFound() {
        super("Room not found");
    }
}
