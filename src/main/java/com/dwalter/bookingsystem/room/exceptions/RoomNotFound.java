package com.dwalter.bookingsystem.room.exceptions;

public class RoomNotFound extends RuntimeException{
    public RoomNotFound() {
        super("Room not found");
    }
}
