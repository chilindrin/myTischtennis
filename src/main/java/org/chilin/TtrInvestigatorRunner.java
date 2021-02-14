package org.chilin;

import org.chilin.config.MyTTConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TtrInvestigatorRunner implements CommandLineRunner {

    @Autowired
    private MyTTConfig myTTConfig;

    public static void main( String[] args ){
        SpringApplication.run(TtrInvestigatorRunner.class);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("using environment: " + myTTConfig.getEnvironment());
        System.out.println("name: " + myTTConfig.getName());
        System.out.println("enabled:" + myTTConfig.isEnabled());
        System.out.println("servers: " + myTTConfig.getServers());
    }
}
