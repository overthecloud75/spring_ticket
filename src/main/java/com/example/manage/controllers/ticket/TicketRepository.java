package com.example.manage.controllers.ticket;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketRepository extends MongoRepository<Ticket, String> {
    Page<Ticket> findAll(Pageable pageable);
    Ticket findByTicket(String ticket);
}