package com.example.demo.advice;

import com.example.demo.exception.BookingNotFoundException;
import com.example.demo.exception.SeatAlreadyBookedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SeatAlreadyBookedException.class)
    public ResponseEntity<String> handleSeatBooked(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT) // 409
                .body(ex.getMessage());
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<String> handleNotFound(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND) // 404
                .body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 400
                .body(ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class) // 本来我们要捕获乐观锁ObjectOptimisticLockingFailureException的异常，但是添加了事务，数据库中Booking和Seat的关系又是onetoone的关系，因此插入同样的seatId时候先报数据插入的错误
    public ResponseEntity<String> handleDB(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Seat already booked");
    }
}
