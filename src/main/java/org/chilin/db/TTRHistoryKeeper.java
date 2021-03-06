package org.chilin.db;

import org.chilin.common.vo.TTRHistoryVO;
import org.chilin.db.model.TTRHistory;
import org.chilin.db.service.TTRHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TTRHistoryKeeper {

    @Autowired
    private TTRHistoryService ttrHistoryService;

    public TTRHistoryVO getTtrFromDb(){
        TTRHistory lastTtrHistory = ttrHistoryService.getLastTtrHistory();
        TTRHistoryVO target = convertEntityIntoVO(lastTtrHistory);
        return target;
    }

    private TTRHistoryVO convertEntityIntoVO(TTRHistory source) {
        TTRHistoryVO target = new TTRHistoryVO();
        target.setFecha(source.getFecha());
        target.setTtr(source.getTtr());
        target.setQttr(source.getQttr());
        return target;
    }

}
