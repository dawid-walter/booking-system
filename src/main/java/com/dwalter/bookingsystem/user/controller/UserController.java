package com.dwalter.bookingsystem.user.controller;

import com.dwalter.bookingsystem.user.dto.UserDto;
import com.dwalter.bookingsystem.user.exceptions.UserNotFoundException;
import com.dwalter.bookingsystem.user.mapper.UserMapper;
import com.dwalter.bookingsystem.user.service.UserDbService;
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
@RequestMapping("/users")
public class UserController {
    private final UserDbService userDbService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> get() {
        log.info("Get users!");
        List<UserDto> users = userMapper.mapToUsersDto(userDbService.getAll());
        return new ResponseEntity<>(users, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> get(@PathVariable final Long id) {
        log.info("Get user by id: " + id);
        UserDto user = userMapper.mapToUserDto(userDbService.getById(id).
                orElseThrow(() -> new UserNotFoundException(id)));
        return new ResponseEntity<>(user, OK);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> create(@RequestBody final UserDto userDto) {
        UserDto user = userMapper.mapToUserDto(userDbService.create(userMapper.mapToUser(userDto)));
        return new ResponseEntity<>(user, CREATED);
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody final UserDto userDto) {
        UserDto updatedUser = userDbService.update(userDto);
        return new ResponseEntity<>(updatedUser, OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final Long id) {
        userDbService.delete(id);
        return new ResponseEntity<>("User deleted successfully", OK);
    }
}
