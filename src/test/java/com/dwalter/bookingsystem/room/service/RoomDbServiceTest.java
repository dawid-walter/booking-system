package com.dwalter.bookingsystem.room.service;

import com.dwalter.bookingsystem.functionality.reservation.dto.ReservationRequest;
import com.dwalter.bookingsystem.functionality.reservation.service.ReservationDbService;
import com.dwalter.bookingsystem.functionality.room.entity.Room;
import com.dwalter.bookingsystem.functionality.room.service.RoomDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RoomDbServiceTest {
    @Autowired
    RoomDbService roomDbService;
    @Autowired
    ReservationDbService reservationDbService;

    @Test
    public void should_add_reservation_to_room() {
        //GIVEN
        Room room = Room.builder()
                .title("Manhattan Mansion")
                .description("In the heart of New York City.")
                .imageUrl("https://lonelyplanetimages.imgix.net/mastheads/GettyImages-538096543_medium.jpg?sharp=10&vib=20&w=1200")
                .pricePerDay(BigDecimal.valueOf(199.99))
                .build();

        Long roomId = roomDbService.create(room).getId();

        ReservationRequest reservationRequest = ReservationRequest.builder()
                .reservationFrom(LocalDate.of(2021, 05, 20))
                .reservationTo(LocalDate.of(2021, 05, 21))
                .roomId(roomId)
                .build();
        //WHEN
        reservationDbService.create(reservationRequest);
        //THEN
        assertThat(roomDbService.getById(roomId).get().getReservations().size()).isEqualTo(1);
    }

    @Test
    public void should_delete_reservation_from_room() {
        //GIVEN
        Room room = Room.builder()
                .title("Manhattan Mansion")
                .description("In the heart of New York City.")
                .imageUrl("https://lonelyplanetimages.imgix.net/mastheads/GettyImages-538096543_medium.jpg?sharp=10&vib=20&w=1200")
                .pricePerDay(BigDecimal.valueOf(199.99))
                .build();

        Long roomId = roomDbService.create(room).getId();

        ReservationRequest reservationRequest = ReservationRequest.builder()
                .reservationFrom(LocalDate.of(2021, 05, 20))
                .reservationTo(LocalDate.of(2021, 05, 21))
                .roomId(roomId)
                .build();
        //WHEN
        Long reservationId = reservationDbService.create(reservationRequest).getId();
        reservationDbService.delete(reservationId);
        //THEN
        assertThat(roomDbService.getById(roomId).get().getReservations().size()).isZero();
    }

    @Test
    public void should_get_rooms_in_dates_range() {
        //GIVEN

        Room room1 = Room.builder()
                .title("Manhattan Mansion")
                .description("In the heart of London.")
                .imageUrl("https://lonelyplanetimages.imgix.net/mastheads/GettyImages-538096543_medium.jpg?sharp=10&vib=20&w=1200")
                .pricePerDay(BigDecimal.valueOf(199.99))
                .build();

        Room room2 = Room.builder()
                .title("Manhattan Mansion")
                .description("In the heart of Witney.")
                .imageUrl("https://lonelyplanetimages.imgix.net/mastheads/GettyImages-538096543_medium.jpg?sharp=10&vib=20&w=1200")
                .pricePerDay(BigDecimal.valueOf(199.99))
                .build();

        Long roomId = roomDbService.create(room1).getId();
        roomDbService.create(room2);

        ReservationRequest reservationsRequest = ReservationRequest.builder()
                .reservationFrom(LocalDate.of(2021, 05, 20))
                .reservationTo(LocalDate.of(2021, 05, 21))
                .roomId(roomId)
                .build();
        reservationDbService.create(reservationsRequest);

        //WHEN
        List<Room> byDateRange = roomDbService.getByDateRange(LocalDate.of(2021, 05, 20), LocalDate.of(2021, 05, 21));

        //THEN
        assertThat(byDateRange.size()).isEqualTo(1);
    }
}
