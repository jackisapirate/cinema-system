package com.example.demo.repository;

import com.example.demo.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByScreeningId(Long screeningId);
}
