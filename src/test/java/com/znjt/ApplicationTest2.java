package com.znjt;

import cn.sits.rjb.common.utils.ExcelUtils;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApplicationTest2 {
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    public Object importExcel(MultipartFile file) {
        List<List<Object>> test = null;
        try {
            test = ExcelUtils.importExcel(file, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationTest2.class, args);
    }

}