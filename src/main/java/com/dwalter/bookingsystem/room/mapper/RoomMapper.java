package com.dwalter.bookingsystem.room.mapper;

import com.dwalter.bookingsystem.room.domain.Room;
import com.dwalter.bookingsystem.room.domain.RoomDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomDto mapToRoomDto(Room room);

    Room mapToRoom(RoomDto roomDto);

    List<RoomDto> mapToRoomsDto(List<Room> rooms);
}
