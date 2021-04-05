package org.chilin.service.db.service;

import org.chilin.db.model.TTRHistory;
import org.chilin.db.service.TTRHistoryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
class TTRHistoryServiceTest {

    @Autowired
    private TTRHistoryService sut;

    @Test
    void findAll() {
        List<TTRHistory> result = sut.findAll();
        assertThat(result.isEmpty(),Matchers.is(false));
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
    void getLastTtrHistory_TwoObjectsWithNowAndLaterTime_OnlyLaterObjectWillBeReturned(){

        Timestamp now = new Timestamp(System.currentTimeMillis());
        long myTimeInMiliseconds = now.getTime();
        long someLaterTime = myTimeInMiliseconds + 1000;
        Timestamp later = new Timestamp(someLaterTime);

        TTRHistory ttrHistoryNow = new TTRHistory();
        ttrHistoryNow.setTtr(1602);
        ttrHistoryNow.setQttr(1799);
        ttrHistoryNow.setFecha(now);

        TTRHistory ttrHistoryLater = new TTRHistory();
        ttrHistoryLater.setTtr(1602);
        ttrHistoryLater.setQttr(1799);
        ttrHistoryLater.setFecha(later);

        Long idInsertedValueNow = sut.insert(ttrHistoryNow);
        Long idInsertedValueLater = sut.insert(ttrHistoryLater);

        TTRHistory lastTtrHistory = sut.getLastTtrHistory();
        assertThat(lastTtrHistory, notNullValue());
        assertThat(lastTtrHistory, is(ttrHistoryLater));

        cleanDB(idInsertedValueNow);
        cleanDB(idInsertedValueLater);
    }

    private void cleanDB(Long idInsertedValue) {
        sut.deleteHistoryIfNecessary(idInsertedValue);
    }
}