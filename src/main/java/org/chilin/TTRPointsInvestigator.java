package org.chilin;

import org.chilin.db.TTRHistoryKeeper;
import org.chilin.mytt.service.TTRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TTRPointsInvestigator {

    @Autowired
    private TTRService myTischtennisService;

    @Autowired
    private TTRHistoryKeeper ttrHistoryDBService;

    @RequestMapping("/notifymeifdifferent")
    public String notifyTTRDifference(){
        Integer ttrPoints = myTischtennisService.getTtrPoints();

        Integer ttrFromDb = ttrHistoryDBService.getTtrFromDb();

        return ttrPoints.intValue() != ttrFromDb.intValue() ? "Different" : "thesame";
    }

}
