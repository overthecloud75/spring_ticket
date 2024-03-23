package com.example.manage.controllers.api;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api")
@RequiredArgsConstructor
@Controller
public class ApiController {

    private final ApiService service;

    @GetMapping("/loads")
    @ResponseBody
    public Map<String, Object> list(Model model) {
        Map<String, Object> loadDict = this.service.getDict();
        return loadDict;
    }
}

