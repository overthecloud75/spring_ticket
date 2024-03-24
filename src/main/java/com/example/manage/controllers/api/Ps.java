package com.example.manage.controllers.api;

import java.time.Instant;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ps {
    @Id
    private String id;

    public Instant timestamp;
    public float cpu;
    public float mem;
}
