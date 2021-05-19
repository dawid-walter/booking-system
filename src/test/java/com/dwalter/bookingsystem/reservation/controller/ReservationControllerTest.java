package com.dwalter.bookingsystem.reservation.controller;

import com.dwalter.bookingsystem.reservation.domain.ReservationDto;
import com.dwalter.bookingsystem.user.service.UserDbService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@Disabled
@WithMockUser(username = "user", password = "password")
@WebMvcTest(ReservationController.class)
class ReservationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationController reservationController;

    @MockBean
    private UserDbService userDbService;

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    @DisplayName("/reservation | GET")
    void should_get_reservations() throws Exception {
        //Given
        ReservationDto reservation1 = ReservationDto.builder()
                .id(1L)
                .reservationFrom(LocalDate.of(2021, 4, 19))
                .reservationTo(LocalDate.of(2021, 4, 20))
                .paid(true)
                .build();

        ReservationDto reservation2 = ReservationDto.builder()
                .id(2L)
                .reservationFrom(LocalDate.of(2021, 4, 21))
                .reservationTo(LocalDate.of(2021, 4, 22))
                .paid(true)
                .build();

        ReservationDto reservation3 = ReservationDto.builder()
                .id(3L)
                .reservationFrom(LocalDate.of(2021, 4, 23))
                .reservationTo(LocalDate.of(2021, 4, 24))
                .paid(true)
                .build();

        ResponseEntity<List<ReservationDto>> responseEntity = new ResponseEntity<>(List.of(reservation1, reservation2, reservation3), OK);
        given(reservationController.get()).willReturn(responseEntity);

        mockMvc.perform(get("/reservations"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)));
    }
}
