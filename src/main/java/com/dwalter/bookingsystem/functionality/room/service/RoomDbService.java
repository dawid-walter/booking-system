package com.dwalter.bookingsystem.functionality.room.service;

import com.dwalter.bookingsystem.functionality.comment.model.Comment;
import com.dwalter.bookingsystem.functionality.comment.repository.CommentRepository;
import com.dwalter.bookingsystem.functionality.room.controller.dto.RoomDto;
import com.dwalter.bookingsystem.functionality.room.controller.dto.UpdateRoomRequest;
import com.dwalter.bookingsystem.functionality.room.exceptions.RoomInDatesNotAvailable;
import com.dwalter.bookingsystem.functionality.room.exceptions.RoomNotFoundByIdException;
import com.dwalter.bookingsystem.functionality.room.mapper.RoomMapper;
import com.dwalter.bookingsystem.functionality.room.model.Room;
import com.dwalter.bookingsystem.functionality.room.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomDbService {
    private final RoomRepository roomRepository;
    private final CommentRepository commentRepository;

    public Optional<Room> getById(Long id) {
        return roomRepository.findById(id);
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    public List<Room> getAllIncludingCommentsAndReservations() {
        return roomRepository.findAllIncludingCommentsAnReservations();
    }

    public Room create(Room room) {
        roomRepository.save(room);
        return room;
    }

    @Transactional
    public RoomDto update(UpdateRoomRequest updateRoomrequest) {
        Room room = roomRepository.findById(updateRoomrequest.getId())
                .orElseThrow(() -> new RoomNotFoundByIdException(updateRoomrequest.getId()));
        room.setTitle(updateRoomrequest.getTitle() != null ? updateRoomrequest.getTitle() : room.getTitle());
        room.setCapacity(updateRoomrequest.getCapacity() != 0 ? updateRoomrequest.getCapacity() : room.getCapacity());
        room.setColor(updateRoomrequest.getColor() != null ? updateRoomrequest.getColor() : room.getColor());
        room.setDescription(updateRoomrequest.getDescription() != null ? updateRoomrequest.getDescription() : room.getDescription());
        room.setPricePerDay(updateRoomrequest.getPricePerDay() != null ? updateRoomrequest.getPricePerDay() : room.getPricePerDay());
        return RoomMapper.mapToRoomDto(room);
    }

    public void delete(Long id) {
        roomRepository.deleteById(id);
    }

    @Transactional
    public List<RoomDto> getByDateRange(LocalDate from, LocalDate to) {
        List<Room> rooms = getAll();
        if (rooms.isEmpty()) {
            throw new RoomInDatesNotAvailable();
        } else {
            return RoomMapper.mapToRoomsDto(rooms.stream()
                    .filter(room ->
                            !room.isRoomDateMatched(from, to))
                    .collect(Collectors.toList()));
        }
    }

    public Room getWithCommentsById(Long id) {
        Optional<Room> byId = roomRepository.findById(id);
        List<Comment> allByRoomId = commentRepository.findAllByRoomId(id);
        Room room = byId.orElseThrow(() -> new RoomNotFoundByIdException(id));
        room.setComments(allByRoomId);
        return room;
    }

    public void deleteAll() {
        roomRepository.deleteAll();
    }


}
