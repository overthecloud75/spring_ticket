package com.example.manage.controllers.error;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ErrorRepository extends MongoRepository<Error, String> {
    Page<Error> findAll(Pageable pageable);
    Page<Error> findByMsg(Pageable pageable, String msg);
    Page<Error> findByMsgAndIp(Pageable pageable, String msg, String ip);
    Page<Error> findByIp(Pageable pageable, String ip);
}

// https://wikidocs.net/160890