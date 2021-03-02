package org.chilin;

import org.chilin.db.TTRDBReader;
import org.chilin.mytt.service.TTRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TTRPointsInvestigator {

    @Autowired
    private TTRService ttrService;

    @Autowired
    private TTRDBReader ttrdbReader;

    @RequestMapping("/notifymeifdifferent")
    public String notifyTTRDifference(){
        Integer ttrPoints = ttrService.getTtrPoints();

        Integer ttrFromDb = ttrdbReader.getTtrFromDb();

        return ttrPoints != ttrFromDb ? "Different" : "thesame";
    }

}
