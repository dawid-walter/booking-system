package com.dwalter.bookingsystem.reservation.service;

import com.dwalter.bookingsystem.reservation.domain.ReservationRequest;
import com.dwalter.bookingsystem.room.domain.Room;
import com.dwalter.bookingsystem.room.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.SECONDS;

@Transactional
@SpringBootTest
class ReservationDbServiceTest {

}
