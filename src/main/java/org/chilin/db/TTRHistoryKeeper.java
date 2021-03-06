package org.chilin.db;

import org.chilin.db.model.TTRHistory;
import org.chilin.db.service.TTRHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TTRHistoryKeeper {

    @Autowired
    private TTRHistoryService ttrHistoryService;

    public Integer getTtrFromDb(){
        TTRHistory lastTtrHistory = ttrHistoryService.getLastTtrHistory();
        return lastTtrHistory.getTtr();
    }
}
