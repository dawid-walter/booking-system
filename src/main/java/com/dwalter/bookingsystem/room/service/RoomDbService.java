package com.dwalter.bookingsystem.room.service;

import com.dwalter.bookingsystem.room.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomDbService {
    private final RoomRepository roomRepository;
}
