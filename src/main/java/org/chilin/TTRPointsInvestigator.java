package org.chilin;

import org.chilin.service.TTRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TTRPointsInvestigator {

    @Autowired
    private TTRService ttrService;

    @RequestMapping("/getttr")
    public String getCurrentTtrPoints(){
        return ttrService.getTtrPoints();
    }

}
