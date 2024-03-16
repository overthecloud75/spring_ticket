package com.example.manage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.manage.controllers.error.Error;
import com.example.manage.controllers.error.ErrorRepository;


@SpringBootTest
class ManageApplicationTests {

    @Autowired
    private ErrorRepository repository;

    @Test
    void testJpa() {        
        // List<Error> all = this.repository.findAll();
        // Error e1= all.get(0);
    }
}
