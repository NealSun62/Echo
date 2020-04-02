package cn.sits.rjb.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * rabbitmq生产者示例
 *
 * @Author YUBIN
 */
@Component // 将该类交给Spring管理
public class RabbitMqProducerDemo {

    private static Logger logger = LoggerFactory.getLogger(RabbitMqProducerDemo.class);

    @Autowired // 注入rabbitmq 模板
    private AmqpTemplate rabbitTemplate;

    /**
     * 发送消息的方法 hello-queue
     */
    public void sendHelloMessage() {
        // 定义消息体
        String message = "RabbitMqProducerDemo hello queue send message";
        rabbitTemplate.convertAndSend("hello-queue",message);
        logger.info("==================RabbitMqProducerDemo hello queue send message success");
    }

    /**
     * 发送消息的方法 user-queue
     */
    public void sendUserMessage() {
        // 定义消息体
        String message = "RabbitMqProducerDemo user queue send message";
        rabbitTemplate.convertAndSend("user-queue",message);
        logger.info("===================RabbitMqProducerDemo user queue send message success");
    }

    /**
     * 发送消息的方法 work-queue
     */
    public void sendWorkMessage(String message) {
        // 定义消息体
        rabbitTemplate.convertAndSend("work-queue",message);
        logger.info("==================RabbitMqProducerDemo work queue send success:" + message);
    }

    @RabbitListener(queues = "work-queue")
    public void executeWork1(String message) {
        logger.info("executeWork1================接收到的消息是:" + message);
    }

    @RabbitListener(queues = "work-queue")
    public void executeWork2(String message) throws InterruptedException {
        Thread.sleep(100);
        logger.info("executeWork2================接收到的消息是:" + message);
    }
}