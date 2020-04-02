package com.znjt;

import cn.sits.rjb.config.rabbitmq.RabbitSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private static RabbitSender sender;

    @Test
    public static void main(String[] args) throws Exception  {
        try{
            test();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void test() {
        sender.send();
    }

}