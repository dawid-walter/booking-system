package com.dwalter.bookingsystem.functionality.room.controller;


import com.dwalter.bookingsystem.functionality.room.mapper.RoomMapper;
import com.dwalter.bookingsystem.functionality.room.service.RoomDbService;
import com.dwalter.bookingsystem.functionality.room.controller.dto.RoomDto;
import com.dwalter.bookingsystem.functionality.room.exceptions.RoomNotFoundByIdException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping
    public ResponseEntity<List<RoomDto>> get() {
        log.info("Get rooms!");
        List<RoomDto> rooms = RoomMapper.mapToRoomsDto(roomDbService.getAllIncludingCommentsAndReservations());
        return new ResponseEntity<>(rooms, OK);
    }

    @GetMapping("/inDate")
    public ResponseEntity<List<RoomDto>> getInDateRange(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
        log.info("Get rooms available from: " + from + " to: " + to);
        List<RoomDto> rooms = RoomMapper.mapToRoomsDto(roomDbService.getByDateRange(from, to));
        return new ResponseEntity<>(rooms, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> get(@PathVariable final Long id) {
        log.info("Get rooms by id: " + id);
        RoomDto room = RoomMapper.mapToRoomDto(roomDbService.getById(id)
                .orElseThrow(() -> new RoomNotFoundByIdException(id)));
        return new ResponseEntity<>(room, OK);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<RoomDto> create(@RequestBody final RoomDto roomDto) {
        RoomDto room = RoomMapper.mapToRoomDto(roomDbService.create(RoomMapper.mapToRoom(roomDto)));
        return new ResponseEntity<>(room, CREATED);
    }

    @PutMapping
    public ResponseEntity<RoomDto> update(@RequestBody final RoomDto roomDto) {
        RoomDto updatedRoom = RoomMapper.mapToRoomDto(roomDbService.update(roomDto));
        return new ResponseEntity<>(updatedRoom, OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final Long id) {
        roomDbService.delete(id);
        return new ResponseEntity<>("Room Deleted Successfully!", OK);
    }
}


