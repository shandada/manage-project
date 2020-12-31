package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mapper")
public class ManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
        System.out.println("启动成功  !  !  ! ");
        System.out.println("启动成功  !  !  ! ");
        System.out.println("启动成功  !  !  ! ");
    }
}
