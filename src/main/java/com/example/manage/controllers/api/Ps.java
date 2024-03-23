package com.example.manage.controllers.api;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ps {
    @Id
    private String id;

    public LocalDateTime timestamp;
    public float cpu;
    public float mem;
}
