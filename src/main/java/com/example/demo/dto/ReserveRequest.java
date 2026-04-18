package com.example.demo.dto;

import lombok.Data;

@Data
public class ReserveRequest {
    private Long userId;
    private Long seatId;
}
