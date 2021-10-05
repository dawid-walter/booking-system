package com.dwalter.bookingsystem.functionality.room.mapper;

import com.dwalter.bookingsystem.functionality.comment.controller.dto.CommentDto;
import com.dwalter.bookingsystem.functionality.reservation.controller.dto.ReservationDto;
import com.dwalter.bookingsystem.functionality.room.controller.dto.UpdateRoomRequest;
import com.dwalter.bookingsystem.functionality.room.model.Room;
import com.dwalter.bookingsystem.functionality.room.controller.dto.RoomDto;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomMapper {
    private RoomMapper() {
    }

    public static Room mapToRoom(RoomDto roomDto) {
        return Room.builder()
                .id(roomDto.getId())
                .capacity(roomDto.getCapacity())
                .description(roomDto.getDescription())
                .imageUrl(roomDto.getImageUrl())
                .pricePerDay(roomDto.getPricePerDay())
                .color(roomDto.getColor())
                .build();
    }

    public static RoomDto mapToRoomDto(final Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .capacity(room.getCapacity())
                .color(room.getColor())
                .title(room.getTitle())
                .description(room.getDescription())
                .imageUrl(room.getImageUrl())
                .pricePerDay(room.getPricePerDay())
                .reservations(room.getReservations().stream()
                        .map(reservation ->
                                ReservationDto.builder()
                                        .id(reservation.getId())
                                        .reservationFrom(reservation.getReservationFrom())
                                        .reservationTo(reservation.getReservationTo())
                                        .roomId(room.getId())
                                        .build())
                        .collect(Collectors.toList()))
                .comments(room.getComments().stream()
                        .map(comment ->
                                CommentDto.builder()
                                        .id(comment.getId())
                                        .content(comment.getContent())
                                        .created(comment.getCreated())
                                        .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public static List<RoomDto> mapToRoomsDto(final List<Room> rooms) {
        return rooms.stream()
                .map(room ->
                        RoomDto.builder()
                                .id(room.getId())
                                .capacity(room.getCapacity())
                                .color(room.getColor())
                                .description(room.getDescription())
                                .pricePerDay(room.getPricePerDay())
                                .imageUrl(room.getImageUrl())
                                .title(room.getTitle())
                                .reservations(room.getReservations().stream()
                                        .map(reservation ->
                                                ReservationDto.builder()
                                                        .id(reservation.getId())
                                                        .reservationFrom(reservation.getReservationFrom())
                                                        .reservationTo(reservation.getReservationTo())
                                                        .roomId(room.getId())
                                                        .build())
                                        .collect(Collectors.toList()))
                                .comments(room.getComments().stream()
                                        .map(comment ->
                                                CommentDto.builder()
                                                        .id(comment.getId())
                                                        .content(comment.getContent())
                                                        .created(comment.getCreated())
                                                        .build())
                                        .collect(Collectors.toList()))
                                .build()
                )
                .collect(Collectors.toList());
    }

    public static UpdateRoomRequest mapToUpdateRoom(Room room) {
        return UpdateRoomRequest.builder()
                .id(room.getId())
                .capacity(room.getCapacity())
                .color(room.getColor())
                .description(room.getDescription())
                .imageUrl(room.getImageUrl())
                .pricePerDay(room.getPricePerDay())
                .title(room.getTitle())
                .build();

    }
}
