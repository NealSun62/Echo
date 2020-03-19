package com.znjt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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