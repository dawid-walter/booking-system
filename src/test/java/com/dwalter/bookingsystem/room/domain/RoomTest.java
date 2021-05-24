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

class RoomTest {
    private static Room getTestData() {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(Reservation.builder()
                .reservationFrom(LocalDate.of(2021, 5, 21))
                .reservationTo(LocalDate.of(2021, 5, 24))
                .build());
        return Room.builder().reservations(reservations).build();
    }

    private static Stream<Arguments> roomParamsInDates() {
        Room room = getTestData();

        return Stream.of(
                Arguments.arguments(room, LocalDate.of(2021, 5, 21), LocalDate.of(2021, 5, 24)),
                Arguments.arguments(room, LocalDate.of(2021, 5, 20), LocalDate.of(2021, 5, 25)),
                Arguments.arguments(room, LocalDate.of(2021, 5, 22), LocalDate.of(2021, 5, 24)),
                Arguments.arguments(room, LocalDate.of(2021, 5, 20), LocalDate.of(2021, 5, 23))
        );
    }

    private static Stream<Arguments> roomParamsOutOfDates() {
        Room room = getTestData();

        return Stream.of(
                Arguments.arguments(room, LocalDate.of(2021, 5, 10), LocalDate.of(2021, 5, 12)),
                Arguments.arguments(room, LocalDate.of(2021, 5, 25), LocalDate.of(2021, 5, 27))
        );
    }

    @ParameterizedTest(name = "{index} ==> Room is booked from {1} to {2}")
    @MethodSource("roomParamsInDates")
    void should_return_true_if_room_is_booked_in_given_dates(Room room, LocalDate from, LocalDate to) {
        assertThat(room.isRoomDateMatched(from, to)).isTrue();
    }

    @ParameterizedTest(name = "{index} ==> Room is not booked from {1} to {2}")
    @MethodSource("roomParamsOutOfDates")
    void should_return_false_if_room_is_not_booked_in_given_dates(Room room, LocalDate from, LocalDate to) {
        assertThat(room.isRoomDateMatched(from, to)).isFalse();
    }
}
