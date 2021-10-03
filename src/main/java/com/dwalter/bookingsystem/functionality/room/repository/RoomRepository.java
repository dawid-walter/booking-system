package com.dwalter.bookingsystem.functionality.room.repository;

import com.dwalter.bookingsystem.functionality.room.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select r from Room r" +
            " left join fetch r.comments" +
            " left join fetch r.reservations")
    List<Room> findAllIncludingCommentsAnReservations();
}
