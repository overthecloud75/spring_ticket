package com.example.manage.access;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Access {
    @Id
    private String id;

    private LocalDateTime timestamp;
    private String ip;
    private String geo_ip;
    private String host;
    private String http_version;
    private String scheme;
    private String method;
    private String referer;
    private String request_time;
    private Integer size;
    private Integer status;
    private String url;
    private String content_type;
    private String user_agent;
    private String body;
    private String cookie;
}
