package org.chilin.service.db.service;

import org.chilin.db.model.TTRHistory;
import org.chilin.db.service.TTRHistoryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
class TTRHistoryServiceTest {

    @Autowired
    private TTRHistoryService sut;

    @Test
    void findAll() {
        sut.findAll();
    }

    @Test
    void insert(){
        TTRHistory ttrHistory = new TTRHistory();
        ttrHistory.setTtr(1602);
        ttrHistory.setQttr(1799);
        ttrHistory.setFecha(new Timestamp(System.currentTimeMillis()));
        Long idInsertedValue = sut.insert(ttrHistory);
        assertThat(idInsertedValue,Matchers.not(0));

        cleanDB(idInsertedValue);
    }

    @Test
    void testGet(){
        TTRHistory lastTtrHistory = sut.getLastTtrHistory();
        assertThat(lastTtrHistory, notNullValue());
    }

    private void cleanDB(Long idInsertedValue) {
        sut.deleteHistoryIfNecessary(idInsertedValue);
    }
}