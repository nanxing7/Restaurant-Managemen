package com.fyd.cygl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fyd.cygl.dao")
public class CyglApplication {
    public static void main(String[] args) {
        SpringApplication.run(CyglApplication.class, args);
    }

}
