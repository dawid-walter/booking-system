package com.dwalter.bookingsystem.room.controller;


import com.dwalter.bookingsystem.room.domain.RoomDto;
import com.dwalter.bookingsystem.room.mapper.RoomMapper;
import com.dwalter.bookingsystem.room.service.RoomDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

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
    public ResponseEntity<RoomDto> getJob(@PathVariable final Long id) {
        RoomDto room = roomMapper.mapToRoomDto(roomDbService.getById(id).orElseThrow(() -> new IllegalStateException("User not found")));
        return new ResponseEntity<>(room, OK);
    }
}


