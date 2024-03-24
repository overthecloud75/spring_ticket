package com.example.manage.controllers.ping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PingController {

    @GetMapping("/ping/")
    @ResponseBody
    public String list() {
        return "";
    }
}