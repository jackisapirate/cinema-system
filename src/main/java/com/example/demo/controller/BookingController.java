package com.example.demo.controller;

import com.example.demo.service.SeatService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final SeatService service;

    public BookingController(SeatService service) {
        this.service = service;
    }

    @PostMapping("/{id}/confirm")
    public String confirm(@PathVariable Long id) {
        service.confirm(id);
        return "SUCCESS";
    }
}
