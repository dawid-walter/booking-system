package com.dwalter.bookingsystem;

import com.dwalter.bookingsystem.reservation.domain.Reservation;
import com.dwalter.bookingsystem.reservation.repository.ReservationRepository;
import com.dwalter.bookingsystem.room.domain.Room;
import com.dwalter.bookingsystem.room.repository.RoomRepository;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Getter
@ToString
public class Start {
    private final RoomRepository roomRepo;
    private final ReservationRepository reservationRepo;

    public Start(RoomRepository roomRepo, ReservationRepository reservationRepo) {
        this.roomRepo = roomRepo;
        this.reservationRepo = reservationRepo;

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

        reservationRepo.save(Reservation.builder()
                .reservationFrom(LocalDate.of(2021, 4, 19))
                .reservationTo(LocalDate.of(2021, 4, 20))
                .paid(true)
                .build());
    }
}
