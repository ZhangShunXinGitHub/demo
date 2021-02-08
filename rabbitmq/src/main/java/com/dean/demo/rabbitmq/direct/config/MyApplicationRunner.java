package com.dean.demo.rabbitmq.direct.config;

import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private final RabbitListenerEndpointRegistry registry;
    @Value("${server.port}")
    private int port;


    @Autowired
    public MyApplicationRunner(RabbitListenerEndpointRegistry registry) {
        this.registry =  registry;
    }

    @Override
    public void run(ApplicationArguments args) {
        //执行一些数据初始化代码（省略）
        //得到容器的对象
        MessageListenerContainer container = registry.getListenerContainer("agent_regiest_queue");
        //判断容器状态
        if(!container.isRunning() && port==8082){
            //开启容器
            container.start();
            System.out.println("开启容器");
        }else {
            System.out.println("不开启");
        }
    }
}
