package com.example.manage.error;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ErrorController {

    private final ErrorService service;
    private String nonce = UUID.randomUUID().toString();
    private List<String> headerList = new ArrayList<>(List.of("Occurance", "Attacker ip", "Country", "Server", "Host", "HTTP Version", "Method", "Url", "Message"));
    private List<String> accessorList = new ArrayList<>(List.of("timestamp", "ip", "geo_ip", "server", "host", "http_version", "method", "url", "msg"));
    private List<String> wafHeaderList = new ArrayList<>(List.of("Occurance", "Attacker ip", "Country", "Host", "HTTP Version", "Method", "Url", "Event ID", "Event Name", "Event Data", "Severity"));
    private List<String> wafAccessorList = new ArrayList<>(List.of("timestamp", "ip", "geo_ip", "host", "http_version", "method", "url", "waf_id", "waf_event", "waf_data", "waf_severity"));

    @GetMapping("/error_log/")
    public String errorList(HttpServletRequest request, Model model, @RequestParam(value="ip", defaultValue="") String ip, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Error> paging = this.service.getList(ip, page); 
        model.addAttribute("uri", request.getRequestURI());
        model.addAttribute("headerList", headerList);
        model.addAttribute("accessorList", accessorList);
        model.addAttribute("ip", ip);
        model.addAttribute("paging", paging);
        model.addAttribute("nonce", nonce);
        return "pages/error_list";
    }

    @GetMapping("/waf_log/")
    public String wafList(HttpServletRequest request, Model model, @RequestParam(value="ip", defaultValue="") String ip, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Error> paging = this.service.getWafList(ip, page);
        model.addAttribute("uri", request.getRequestURI());
        model.addAttribute("headerList", wafHeaderList);
        model.addAttribute("accessorList", wafAccessorList);
        model.addAttribute("ip", ip);
        model.addAttribute("paging", paging);
        model.addAttribute("nonce", nonce);
        return "pages/waf_list";
    }
}