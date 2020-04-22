package com.znjt;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class ApplicationTest {
    private volatile static ApplicationTest applicationTest;

    private ApplicationTest() {
    }

    public static ApplicationTest getApplicationTest() {
        Map map = new HashMap(100);
        if (null == applicationTest) {
            synchronized (ApplicationTest.class) {
                if (null == applicationTest) {
                    applicationTest = new ApplicationTest();
                }
            }
        }
        return applicationTest;
    }
//    @Autowired
//    private static RabbitSender sender;

//    @Test
//    public static void main(String[] args) throws Exception  {
//        try{
//            test();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public static void test() {
//        sender.send();
//    }

}