package com.example.manage.controllers.error;

import java.time.Instant;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error {
    @Id
    private String id;

    private Instant timestamp;
    private String ip;
    private String geo_ip;
    private String host;
    private String http_version;
    private String method;
    private String level;
    private String url;
    private String reason;
    private String server;
    private String msg;
    private String waf_id;
    private String waf_event;
    private String waf_data;
    private String waf_severity;
}
