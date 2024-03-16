package com.example.manage.controllers.access;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccessRepository extends MongoRepository<Access, String> {
    Page<Access> findAll(Pageable pageable);
    Page<Access> findByIp(Pageable pageable, String ip);
    Page<Access> findByHost(Pageable pageable, String host);
    Page<Access> findByIpAndHost(Pageable pageable, String ip, String host);
    Page<Access> findByStatus(Pageable pageable, Integer status);
    Page<Access> findByIpAndStatus(Pageable pageable, String ip, Integer status);
}