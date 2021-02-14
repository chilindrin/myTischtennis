package org.chilin;

import org.chilin.config.MyTTConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PropertiesReaderTest {

    @Autowired
    private MyTTConfig myTTConfig;

    @Test
    public void readConfiguration(){
        String environment = myTTConfig.getEnvironment();

    }
}
