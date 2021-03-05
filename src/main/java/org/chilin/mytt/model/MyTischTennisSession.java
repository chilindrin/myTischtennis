package org.chilin.mytt.model;

import lombok.Data;
import org.chilin.common.vo.TTRHistoryVO;
import org.chilin.mytt.service.MyTischTennisCookieReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class MyTischTennisSession {

    private TTRHistoryVO ttrEntry;

    @Autowired
    private MyTischTennisCookieReader myTischTennisCookieReader;

}
