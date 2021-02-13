package org.chilin.model;

import lombok.Data;
import org.chilin.service.mytischtennis.MyTischTennisCookieReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class MyTischTennisSession {

    private Integer myTtrPoints;

    @Autowired
    private MyTischTennisCookieReader myTischTennisCookieReader;

}
