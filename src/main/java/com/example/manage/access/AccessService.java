package com.example.manage.access;

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
public class AccessService {

    private final AccessRepository repository;

    public Page<Access> getList(String ip, int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("timestamp"));
        Pageable pageable = PageRequest.of(page, 20, Sort.by(sorts));
        if (ip.equals("")) {return this.repository.findAll(pageable);}
        return this.repository.findByIp(pageable, ip);     
    }

    public Page<Access> getSite(String ip, String host, int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("timestamp"));
        Pageable pageable = PageRequest.of(page, 20, Sort.by(sorts));
        if (ip.equals("")) {return this.repository.findByHost(pageable, host);}
        return this.repository.findByIpAndHost(pageable, ip, host);     
    }

    public Page<Access> getStatus(String ip, Integer status, int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("timestamp"));
        Pageable pageable = PageRequest.of(page, 20, Sort.by(sorts));
        if (ip.equals("")) {return this.repository.findByStatus(pageable, status);}
        return this.repository.findByIpAndStatus(pageable, ip, status);     
    }
}
