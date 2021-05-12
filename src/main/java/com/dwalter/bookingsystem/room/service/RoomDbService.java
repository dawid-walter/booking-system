package com.dwalter.bookingsystem.room.service;

import com.dwalter.bookingsystem.room.domain.Room;
import com.dwalter.bookingsystem.room.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomDbService {
    private final RoomRepository roomRepository;

    public Optional<Room> getById(Long id) {
        return roomRepository.findById(id);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
