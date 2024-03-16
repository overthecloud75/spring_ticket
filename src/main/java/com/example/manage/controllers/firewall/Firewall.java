package com.example.manage.controllers.firewall;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Firewall {
    private String ip;
    private String ip_class;
    private String protocol;
    private String port;
    private String block;
    private String message;
}
