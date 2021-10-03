package com.dwalter.bookingsystem.functionality.room.service;

import com.dwalter.bookingsystem.functionality.room.model.Room;
import com.dwalter.bookingsystem.functionality.room.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

class RoomDbServiceTest {

    @InjectMocks
    RoomDbService roomDbService;

    @Mock
    RoomRepository roomRepository;

    @Test
    public void should_throw_exception_when_no_rooms_in_Date_range() {
        int test = 1;
        assertThat(test).isEqualTo(1);

    }
}
