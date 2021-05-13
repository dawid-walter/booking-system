package com.dwalter.bookingsystem.room.service;

import com.dwalter.bookingsystem.room.domain.Room;
import com.dwalter.bookingsystem.room.domain.RoomDto;
import com.dwalter.bookingsystem.room.exceptions.RoomNotFoundByIdException;
import com.dwalter.bookingsystem.room.mapper.RoomMapper;
import com.dwalter.bookingsystem.room.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomDbService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public Optional<Room> getById(Long id) {
        return roomRepository.findById(id);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room create(Room room) {
        roomRepository.save(room);
        return room;
    }

    @Transactional
    public RoomDto update(RoomDto roomDto) {
        Room room = roomRepository.findById(roomDto.getId())
                .orElseThrow(() -> new RoomNotFoundByIdException(roomDto.getId()));
        room.setTitle(roomDto.getTitle());
        room.setCapacity(roomDto.getCapacity());
        room.setColor(roomDto.getColor());
        room.setDescription(roomDto.getDescription());
        return roomMapper.mapToRoomDto(room);
        
    }

    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}
