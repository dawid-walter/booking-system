package com.dwalter.bookingsystem.room.repository;

import com.dwalter.bookingsystem.room.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
