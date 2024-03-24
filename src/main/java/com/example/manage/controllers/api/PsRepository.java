package com.example.manage.controllers.api;

import java.time.Instant;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PsRepository extends MongoRepository<Ps, String> {

    List<Ps> findByTimestampBetweenOrderByTimestampDesc(Instant oneDayAgo, Instant currentDateTime);
}
