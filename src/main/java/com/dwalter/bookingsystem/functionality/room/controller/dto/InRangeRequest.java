package com.dwalter.bookingsystem.functionality.room.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
public class InRangeRequest {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate from;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate to;
}
