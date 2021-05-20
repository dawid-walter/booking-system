package com.dwalter.bookingsystem.room.domain;

import com.dwalter.bookingsystem.reservation.domain.Reservation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    private static Stream<Arguments> roomParams() {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(Reservation.builder()
                .reservationFrom(LocalDate.now())
                .reservationTo(LocalDate.now().plusDays(2))
                .build());
        return Stream.of(
                Arguments.arguments(Room.builder().reservations(reservations).build(), LocalDate.now(), LocalDate.now().plusDays(2))
        );
    }

    @ParameterizedTest
    @MethodSource("roomParams")
    void should_return_true_if_room_is_booked(Room room, LocalDate from, LocalDate to) {
        assertThat(room.isRoomDateMatched(from, to)).isTrue();
    }
}
