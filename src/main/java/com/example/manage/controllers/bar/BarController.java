package com.example.manage.controllers.bar;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BarController {
    private String nonce = UUID.randomUUID().toString();

    @GetMapping("/bar_chart/")
    public String list(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());
        model.addAttribute("nonce", nonce);
        model.addAttribute("apiUrl", "/api/bar");
        return "pages/bar";
    }
}
