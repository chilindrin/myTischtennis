package org.chilin.service.db.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TTRHistoryServiceTest {

    @Autowired
    private TTRHistoryService sut;

    @Test
    void findAll() {
        sut.findAll();
    }
}