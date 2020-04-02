package cn.sits.rjb.tool;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.tool
 * @date 2020/03/27 16:11
 */

import cn.sits.rjb.config.rabbitmq.RabbitConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestMQ {
    // 监听队列
    @RabbitListener(queues = {RabbitConfig.QUEUE})
    public void lisen(User msg, Message message, Channel channel) {
        // msg是收到的信息，message则是以固定方式封装的信息
        // 这里发送的消息是一个序列化后的对象，一般也可以使用jsonString传递消息，这样接收的话可以使用string接收
        log.info("recive a massage:" + msg);
    }
}