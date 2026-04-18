package com.example.demo.exception;

public class SeatAlreadyBookedException extends RuntimeException {
    public SeatAlreadyBookedException(String msg) {
        super(msg);
    }
}
