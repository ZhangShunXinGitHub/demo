package rabbitmq.direct.config;


import cn.hutool.json.JSONObject;
import com.dean.dean.common.constant.RabbitMqConstant;
import com.dean.dean.common.dto.User;
import com.dean.dean.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@Component
@Slf4j
public class Sender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDirectQueue(User user) throws Exception {
        //[] userByte= JsonUtil.obj2String(user).getBytes();
        byte[] userByte= getBytesFromObject(user);
        // 第一个参数是指要发送到哪个队列里面， 第二个参数是指要发送的内容
        rabbitTemplate.convertAndSend(RabbitMqConstant.QUEUE, userByte);
        log.info("【sendDirectQueue已发送消息】id={}",user.getUserId());
    }
    //对象转化为字节码
    public  byte[] getBytesFromObject(Serializable obj) throws Exception {
        if (obj == null) {
            return null;
        }
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(obj);
        return bo.toByteArray();
    }
}
