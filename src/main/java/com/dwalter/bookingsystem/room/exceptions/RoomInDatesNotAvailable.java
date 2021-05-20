package com.dwalter.bookingsystem.room.exceptions;

public class RoomInDatesNotAvailable extends RuntimeException {
    public RoomInDatesNotAvailable() {
        super("There are no rooms available in chosen dates");
    }
}
