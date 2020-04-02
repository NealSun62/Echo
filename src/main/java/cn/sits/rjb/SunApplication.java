package cn.sits.rjb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
@EnableScheduling
@MapperScan("cn.sits.rjb")
public class SunApplication extends SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(SunApplication.class, args);
    }
}
