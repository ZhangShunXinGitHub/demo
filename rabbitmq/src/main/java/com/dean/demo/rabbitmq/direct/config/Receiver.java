package com.dean.demo.rabbitmq.direct.config;

import com.dean.dean.common.constant.RabbitMqConstant;
import com.dean.dean.common.dto.User;
import com.dean.dean.common.utils.JsonUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;

@Component
@Slf4j
public class Receiver {

    //queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMqConstant.QUEUE,autoStartup= "false",id = "agent_regiest_queue")
    @RabbitHandler
    public void receiverDirecQueue(Message message, Channel channel) throws Exception {
        // 采用手动应答模式, 手动确认应答更为安全稳定
               channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
              User user= (User) getObjectFromBytes(message.getBody());
               log.info("【receiverDirectQueue监听到消息】 id=" + user.getUserId());

    }
    //字节码转化为对象
    public  Object getObjectFromBytes(byte[] objBytes) throws Exception {
        if (objBytes == null || objBytes.length == 0) {
            return null;
        }
        ByteArrayInputStream bi = new ByteArrayInputStream(objBytes);
        ObjectInputStream oi = new ObjectInputStream(bi);
        return oi.readObject();
    }

}
