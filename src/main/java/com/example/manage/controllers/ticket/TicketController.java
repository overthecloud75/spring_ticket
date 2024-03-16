package com.example.manage.controllers.ticket;

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
public class TicketController {

    private final TicketService service;
    private List<String> headerList = new ArrayList<>(List.of("Occurance", "Ticket", "Site", "Attacker ip", "Country", "Attack No", "Attack FRE", "Attack FRE(C Class)", "Origin", "Action", "Action Time"));
    private List<String> accessorList = new ArrayList<>(List.of("timestamp", "ticket", "site", "ip", "geo_ip", "attack_no", "attack_w", "attack_24", "origin", "fix", "fix_timestamp"));
    private String nonce = UUID.randomUUID().toString();

    @GetMapping("/ticket/")
    public String list(HttpServletRequest request, Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Ticket> paging = this.service.getList(page);
        model.addAttribute("uri", request.getRequestURI());
        model.addAttribute("headerList", headerList);
        model.addAttribute("accessorList", accessorList);
        model.addAttribute("paging", paging);
        model.addAttribute("nonce", nonce);
        return "pages/ticket_list";
    }

    @GetMapping("/ticket/{ticket}")
    public String ticketAccessList(HttpServletRequest request, Model model, @PathVariable("ticket") String ticket) {
        Ticket ticketWithIp = this.service.getByTicket(ticket);
        String ip = ticketWithIp.getIp();
        model.addAttribute("uri", request.getRequestURI());
        model.addAttribute("nonce", nonce);
        return "redirect:/access_log/?ip=" + ip;
    }
}