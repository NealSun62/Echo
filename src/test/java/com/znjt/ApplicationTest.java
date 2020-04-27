package com.znjt;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    public static long trailingZeros(long n) {
        long t = 0;
        while (n >= 5) {
            n = n / 5;
            t = t + n;
        }
        return t;
    }

    public static void main(String[] args) {
        long n = 105;
        long result = trailingZeros(n);
        System.out.println("result = " + result);
    }

}