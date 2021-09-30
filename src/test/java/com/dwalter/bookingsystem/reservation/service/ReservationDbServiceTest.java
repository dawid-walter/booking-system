package com.dwalter.bookingsystem.reservation.service;

import com.dwalter.bookingsystem.functionality.reservation.dto.ReservationRequest;
import com.dwalter.bookingsystem.functionality.reservation.service.ReservationDbService;
import com.dwalter.bookingsystem.functionality.room.entity.Room;
import com.dwalter.bookingsystem.functionality.room.service.RoomDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ReservationDbServiceTest {
    @Autowired
    RoomDbService roomDbService;

    @Autowired
    ReservationDbService reservationDbService;

    @Test
    public void should_add_reservation() {
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
        assertThat(reservationDbService.getAll().size()).isEqualTo(1);
    }
}
