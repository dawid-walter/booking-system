package com.dwalter.bookingsystem;

import com.dwalter.bookingsystem.room.domain.Room;
import com.dwalter.bookingsystem.room.repository.RoomRepository;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Getter
@ToString
public class Start {
    private final RoomRepository roomRepo;

    public Start(RoomRepository roomRepo) {
        this.roomRepo = roomRepo;

        roomRepo.save(Room.builder()
                .title("Manhattan Mansion")
                .description("In the heart of New York City.")
                .imageUrl("https://lonelyplanetimages.imgix.net/mastheads/GettyImages-538096543_medium.jpg?sharp=10&vib=20&w=1200")
                .pricePerDay(BigDecimal.valueOf(199.99))
                .build());

        roomRepo.save(Room.builder()
                .title("Manhattan Mansion")
                .description("In the heart of New York City.")
                .imageUrl("https://lonelyplanetimages.imgix.net/mastheads/GettyImages-538096543_medium.jpg?sharp=10&vib=20&w=1200")
                .pricePerDay(BigDecimal.valueOf(199.99))
                .build());
    }
}
