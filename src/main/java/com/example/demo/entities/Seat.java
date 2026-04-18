package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Seat {
    //	Seat: id, seatNumber, status (Enum), screeningId, lockedAt (Timestamp), version (for locking).
    @Id
    @GeneratedValue
    private Long Id;

    private String seatNumber; // A1, A2

//    public enum Status{AVAILABLE, LOCKED, SOLD};
    @Enumerated(EnumType.STRING)
    private Status status; // 作为是否被占用
    private LocalDateTime lockedAt;

    @Version
    private Long version;

    @ManyToOne
    private Screening screening;
}
