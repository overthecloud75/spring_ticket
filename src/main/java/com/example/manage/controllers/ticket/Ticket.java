package com.example.manage.controllers.ticket;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ticket {
    @Id
    private String id;

    private LocalDateTime timestamp;
    private LocalDateTime fix_timestamp;
    private String ticket;
    private String ip;
    private String geo_ip;
    private String str_ip;
    private String site;
    private String action;
    private String model;
    private String level;
    private String fix;
    private String result;
    private String origin;
    private Integer attack_no;
    private Integer attack_w;
    private Integer attack_24;
}
