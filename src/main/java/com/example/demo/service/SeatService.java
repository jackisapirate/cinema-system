package com.example.demo.service;

import com.example.demo.entities.Booking;
import com.example.demo.entities.Seat;
import com.example.demo.entities.Status;
import com.example.demo.exception.SeatAlreadyBookedException;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.SeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SeatService {

    private final SeatRepository seatRepo;
    private final BookingRepository bookingRepo;

    public List<Seat> getSeats(Long id){
        return seatRepo.findByScreeningId(id);
    }

    @Transactional
    public Booking reserveSeat(Long screeningId, Long userId, Long seatId) {

        Seat seat = seatRepo.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        // 1️⃣ 判断状态
        if (seat.getStatus() != Status.AVAILABLE) {
            throw new RuntimeException("Seat already taken");
        }

        // 为了触发两个users同时预定同一张票的情况，这里让线程休眠数秒
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 2️⃣ 改状态
        seat.setStatus(Status.LOCKED);
        seat.setLockedAt(LocalDateTime.now());

        // 3️⃣ 保存（这里会触发乐观锁）
        try {
            seatRepo.save(seat);
        } catch (Exception e) {
            throw new SeatAlreadyBookedException("Seat already taken by another user");
        }

        // 4️⃣ 创建 booking
        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setSeat(seat);

        return bookingRepo.save(booking);
    }

    @Transactional
    public void confirm(Long bookingId) {

        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Seat seat = booking.getSeat();

        if (seat.getStatus() != Status.LOCKED) {
            throw new RuntimeException("Seat not locked");
        }

        seat.setStatus(Status.SOLD);
        seatRepo.save(seat);
    }
}
