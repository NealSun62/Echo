package cn.sits.rjb.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * RabbitMq消费者示例
 *
 * @Author YUBIN
 * @create 2018-08-08
 */
@Component // 交给Spring管理
//@RabbitListener(queues = {"hello-queue","user-queue"})
public class RabbitMqConsumerDemo {

    private static Logger logger = LoggerFactory.getLogger(RabbitMqConsumerDemo.class);

    //@RabbitHandler
    @RabbitListener(queues = "hello-queue")
    public void executeHello(String message) {
        logger.info("executeHello================接收到的消息是:" + message);
    }

    @RabbitListener(queues = "user-queue")
    public void executeUser(String message) {
        logger.info("executeUser=================接收到的消息是:" + message);
    }
}