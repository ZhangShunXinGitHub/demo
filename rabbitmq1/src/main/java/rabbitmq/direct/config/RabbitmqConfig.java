package rabbitmq.direct.config;

import com.dean.dean.common.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class RabbitmqConfig {
    /**
     * Direct模式
     * @return
     */
    @Bean
    public Queue directQueue() {
        /*
         第一个参数是队列名字， 第二个参数 durable是指是否持久化
         auto-delete 表示消息队列在没有使用时自动删除，默认是false
         exclusive 表示消息队列是否只在当前connection生效，默认是false
         */
        return new Queue(RabbitMqConstant.QUEUE, true);
    }

    /**
     38      * 定制化amqp模版
     39      *
     40      * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调   即消息发送到exchange  ack
     41      * ReturnCallback接口用于实现消息发送到RabbitMQ 交换器，但无相应队列与交换器绑定时的回调  即消息发送不到任何一个队列中  ack
     42      */
     @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
         RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

                // 消息发送失败返回到队列中, yml需要配置 publisher-returns: true
                 rabbitTemplate.setMandatory(true);

               // 消息返回, yml需要配置 publisher-returns: true
                rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
                       // String correlationId = message.getMessageProperties().getCorrelationIdString();
                        String correlationId = message.getMessageProperties().getCorrelationId();
                        log.debug("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
                    });

                // 消息确认, yml需要配置 publisher-confirms: true
                rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
                        if (ack) {
                                // log.debug("消息发送到exchange成功,id: {}", correlationData.getId());
                            } else {
                                log.debug("消息发送到exchange失败,原因: {}", cause);
                            }
                    });

                return rabbitTemplate;
             }

}
