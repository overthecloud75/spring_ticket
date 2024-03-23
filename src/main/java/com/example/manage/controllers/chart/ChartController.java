package com.example.manage.controllers.chart;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ChartController {
    private String nonce = UUID.randomUUID().toString();

    @GetMapping("/load_chart/")
    public String list(Model model) {
        model.addAttribute("nonce", nonce);
        model.addAttribute("apiUrl", "/api/loads");
        return "pages/load";
    }
}
