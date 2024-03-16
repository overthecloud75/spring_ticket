package com.example.manage.controllers.access;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AccessController {

    private final AccessService service;
    private String nonce = UUID.randomUUID().toString();
    private List<String> headerList = new ArrayList<>(List.of("Occurance", "Attacker ip", "Country", "Status", "Host", "HTTP Version", "Scheme", "Method", "Url", "User Agent", "Content Type", "Body", "Time", "Cookie"));
    private List<String> accessorList = new ArrayList<>(List.of("timestamp", "ip", "geo_ip", "status", "host", "http_version", "scheme", "method", "url", "content_type", "body", "request_time", "cookie"));

    @GetMapping("/access_log/")
    public String list(HttpServletRequest request, Model model, @RequestParam(value="ip", defaultValue="") String ip, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Access> paging = this.service.getList(ip, page); 
        model.addAttribute("uri", request.getRequestURI());
        model.addAttribute("headerList", headerList);
        model.addAttribute("accessorList", accessorList);
        model.addAttribute("ip", ip);
        model.addAttribute("paging", paging);
        model.addAttribute("nonce", nonce);
        return "pages/access_list";
    }

    @GetMapping("/access_log/site/{host}")
    public String siteList(HttpServletRequest request, Model model, @PathVariable("host") String host, @RequestParam(value="ip", defaultValue="") String ip, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Access> paging = this.service.getSite(ip, host, page); 
        model.addAttribute("headerList", headerList);
        model.addAttribute("accessorList", accessorList);
        model.addAttribute("ip", ip);
        model.addAttribute("paging", paging);
        model.addAttribute("nonce", nonce);
        return "pages/access_list";
    }

    @GetMapping("/access_log/status/{status}")
    public String stateList(HttpServletRequest request, Model model, @PathVariable("status") Integer status, @RequestParam(value="ip", defaultValue="") String ip, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Access> paging = this.service.getStatus(ip, status, page); 
        model.addAttribute("headerList", headerList);
        model.addAttribute("accessorList", accessorList);
        model.addAttribute("ip", ip);
        model.addAttribute("paging", paging);
        model.addAttribute("nonce", nonce);
        return "pages/access_list";
    }
}