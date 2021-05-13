package com.dwalter.bookingsystem.room.controller;


import com.dwalter.bookingsystem.room.domain.Room;
import com.dwalter.bookingsystem.room.domain.RoomDto;
import com.dwalter.bookingsystem.room.exceptions.RoomNotFoundByIdException;
import com.dwalter.bookingsystem.room.mapper.RoomMapper;
import com.dwalter.bookingsystem.room.service.RoomDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final RoomDbService roomDbService;
    private final RoomMapper roomMapper;

    @GetMapping
    public ResponseEntity<List<RoomDto>> get() {
        log.info("Get rooms!");
        List<RoomDto> rooms = roomMapper.mapToRoomsDto(roomDbService.getAllRooms());
        return new ResponseEntity<>(rooms, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> get(@PathVariable final Long id) {
        log.info("Get rooms by id: " + id);
        RoomDto room = roomMapper.mapToRoomDto(roomDbService.getById(id).orElseThrow(() -> new RoomNotFoundByIdException(id)));
        return new ResponseEntity<>(room, OK);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<RoomDto> create(@RequestBody final RoomDto roomDto) {
        RoomDto room = roomMapper.mapToRoomDto(roomDbService.create(roomMapper.mapToRoom(roomDto)));
        return new ResponseEntity<>(room, CREATED);
    }

    @PutMapping
    public ResponseEntity<RoomDto> update(@RequestBody final RoomDto roomDto) {
        RoomDto updatedRoom = roomDbService.update(roomDto);
        return new ResponseEntity<>(updatedRoom, CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final Long id) {
        roomDbService.delete(id);
        return new ResponseEntity<>("Room Deleted Successfully!", OK);
    }
}


