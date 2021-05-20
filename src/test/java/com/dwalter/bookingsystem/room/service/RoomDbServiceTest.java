package com.dwalter.bookingsystem.room.service;

import com.dwalter.bookingsystem.room.domain.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class RoomDbServiceTest {
    @Autowired
    RoomDbService roomDbService;

    @Test
    public void should_save_room() {
        //GIVEN
        Room room = Room.builder()
                .title("Manhattan Mansion")
                .description("In the heart of New York City.")
                .imageUrl("https://lonelyplanetimages.imgix.net/mastheads/GettyImages-538096543_medium.jpg?sharp=10&vib=20&w=1200")
                .pricePerDay(BigDecimal.valueOf(199.99))
                .build();
        //WHEN
        roomDbService.create(room);
        //THEN
        assertThat(roomDbService.getAll().size()).isEqualTo(1);
    }
}
