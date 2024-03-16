package com.example.manage.controllers.error;

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
public class ErrorService {

    private final ErrorRepository repository;

    public Page<Error> getList(String ip, int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("timestamp"));
        Pageable pageable = PageRequest.of(page, 20, Sort.by(sorts));
        if (ip.equals("")) {return this.repository.findAll(pageable);}
        return this.repository.findByIp(pageable, ip);
    }

    public Page<Error> getWafList(String ip, int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("timestamp"));
        Pageable pageable = PageRequest.of(page, 20, Sort.by(sorts));
        if (ip.equals("")) {return this.repository.findByMsg(pageable, "403 by WAF");}
        return this.repository.findByMsgAndIp(pageable, "403 by WAF", ip);
    }
}
