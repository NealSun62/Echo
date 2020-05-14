package com.znjt.rjb;

import cn.sits.rjb.common.utils.IPUtil;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    public static void main(String[] args) {
//        String pattern="^((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}$";
        String content = "192.08.10.1";

        boolean isMatch = IPUtil.ipValid(content);
        System.out.println(isMatch);
    }
}