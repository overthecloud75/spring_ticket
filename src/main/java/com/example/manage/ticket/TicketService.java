package com.example.manage.ticket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TicketService {

    private final TicketRepository repository;

    public Page<Ticket> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("timestamp"));
        Pageable pageable = PageRequest.of(page, 20, Sort.by(sorts));
        return this.repository.findAll(pageable);
    }

    public Ticket getByTicket(String ticket) {
        return this.repository.findByTicket(ticket);
    }
}
