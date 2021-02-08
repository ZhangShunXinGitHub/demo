package com.dean.demo.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class RabbitmqAppReceiver {
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqAppReceiver.class,args);
    }
}