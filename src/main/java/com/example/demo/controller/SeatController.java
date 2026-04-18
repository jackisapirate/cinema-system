package com.example.demo.controller;

import com.example.demo.dto.ReserveRequest;
import com.example.demo.entities.Booking;
import com.example.demo.entities.Seat;
import com.example.demo.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/screenings")
public class SeatController {
    private final SeatService seatService;
    public SeatController(SeatService service){
        this.seatService = service;
    }

    @GetMapping("/{id}/seats")
    public List<Seat> getSeats(@PathVariable Long id) {
        return seatService.getSeats(id);
    }
    @PostMapping("/{id}/reserve")
    public Booking reserve(@PathVariable Long id,
                           @RequestBody ReserveRequest req) {
        return seatService.reserveSeat(id, req.getUserId(), req.getSeatId());
    }
}
