package com.sana.bigdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//添加项目中的mapper扫描
@MapperScan("com.sana.bigdata.mapper")
public class BigdataApplication {

    public static void main(String[] args) {

        SpringApplication.run(BigdataApplication.class, args);
    }

}
