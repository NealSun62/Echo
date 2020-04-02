package cn.sits.rjb.config.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.config.rabbitmq
 * @date 2020/03/25 10:01
 */
@Component
@RabbitListener(queues = "hello")
public class RabbitReceiver {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver : " + hello);
    }
}
