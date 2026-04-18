package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Screening {

    @Id
    @GeneratedValue
    private Long id;

    private String movieTitle;
}
