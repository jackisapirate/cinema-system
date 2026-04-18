package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue
    private Long bookingId;

    private Long userId;

    @OneToOne
    private Seat seat;
}
