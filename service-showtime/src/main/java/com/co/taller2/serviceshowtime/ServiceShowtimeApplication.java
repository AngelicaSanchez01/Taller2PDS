package com.co.taller2.serviceshowtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
public class ServiceShowtimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceShowtimeApplication.class, args);
    }

}
