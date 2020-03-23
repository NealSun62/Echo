package com.znjt;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

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
        LocalDate today = LocalDate.now();
        boolean a = today.isLeapYear();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        System.out.println(a);
        System.out.println("year:"+year);
        System.out.println("month:"+month);
        System.out.println("day:"+day);
    }

}