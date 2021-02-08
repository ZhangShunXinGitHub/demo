package com.dean.demo.rabbitmq.direct.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import static com.dean.dean.common.constant.RabbitMqConstant.QUEUE;


@Configuration
@Slf4j
public class RabbitmqConfig {

    /**
     * Direct模式
     * @return
     */
    @Bean
    public Queue directQueue() {
        return new Queue(QUEUE, true);
    }

}
