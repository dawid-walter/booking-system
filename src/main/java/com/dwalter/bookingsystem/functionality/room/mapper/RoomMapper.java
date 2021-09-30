package com.dwalter.bookingsystem.functionality.room.mapper;

import com.dwalter.bookingsystem.functionality.reservation.controller.dto.ReservationDto;
import com.dwalter.bookingsystem.functionality.room.model.Room;
import com.dwalter.bookingsystem.functionality.room.controller.dto.RoomDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomMapper {
    public Room mapToRoom(RoomDto roomDto) {
        return Room.builder()
                .id(roomDto.getId())
                .capacity(roomDto.getCapacity())
                .description(roomDto.getDescription())
                .imageUrl(roomDto.getImageUrl())
                .pricePerDay(roomDto.getPricePerDay())
                .color(roomDto.getColor())
                .build();
    }

    public RoomDto mapToRoomDto(final Room room) {
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
                .build();
    }

    public List<RoomDto> mapToRoomsDto(final List<Room> rooms) {
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
                                .build()
                )
                .collect(Collectors.toList());
    }
}
