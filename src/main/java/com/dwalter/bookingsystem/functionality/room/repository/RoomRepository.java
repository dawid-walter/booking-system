package com.dwalter.bookingsystem.functionality.room.repository;

import com.dwalter.bookingsystem.functionality.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
