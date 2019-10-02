package com.litse.dbservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



//@EnableCircuitBreaker
//@EnableHystrixDashboard
@SpringBootApplication
//@EnableEurekaClient
public class DbServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbServiceApplication.class, args);
    }

}
