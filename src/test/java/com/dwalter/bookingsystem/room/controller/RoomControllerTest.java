package com.dwalter.bookingsystem.room.controller;

import com.dwalter.bookingsystem.functionality.reservation.controller.ReservationController;
import com.dwalter.bookingsystem.functionality.reservation.mapper.ReservationMapper;
import com.dwalter.bookingsystem.functionality.reservation.service.ReservationDbService;
import com.dwalter.bookingsystem.functionality.room.controller.RoomController;
import com.dwalter.bookingsystem.functionality.room.dto.RoomDto;
import com.dwalter.bookingsystem.user.service.UserDbService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservationController.class)
class RoomControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomController roomController;

    @MockBean
    private UserDbService userDbService;

    @MockBean
    private ReservationDbService reservationDbService;

    @MockBean
    private ReservationMapper reservationMapper;

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    @DisplayName("/rooms | GET")
    void should_get_rooms() throws Exception {
        RoomDto room1 = RoomDto.builder()
                .id(1L)
                .title("Manhattan Mansion")
                .description("In the heart of London.")
                .imageUrl("https://lonelyplanetimages.imgix.net/mastheads/GettyImages-538096543_medium.jpg?sharp=10&vib=20&w=1200")
                .pricePerDay(BigDecimal.valueOf(199.99))
                .build();

        RoomDto room2 = RoomDto.builder()
                .id(2L)
                .title("Manhattan Mansion")
                .description("In the heart of Witney.")
                .imageUrl("https://lonelyplanetimages.imgix.net/mastheads/GettyImages-538096543_medium.jpg?sharp=10&vib=20&w=1200")
                .pricePerDay(BigDecimal.valueOf(199.99))
                .build();

        ResponseEntity<List<RoomDto>> responseEntity = new ResponseEntity<>(List.of(room1, room2), OK);

        given(roomController.get()).willReturn(responseEntity);

        mockMvc.perform(get("/rooms"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)));
    }
}
