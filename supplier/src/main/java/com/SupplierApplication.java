package com;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mapper")
public class SupplierApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupplierApplication.class, args);
        System.out.println("2. supplier启动成功 ! ! !");
        System.out.println("2. supplier启动成功 ! ! !");
        System.out.println("2. supplier启动成功 ! ! !");
    }

}
