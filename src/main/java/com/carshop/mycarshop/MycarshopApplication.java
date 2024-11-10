package com.carshop.mycarshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan    // @EnableConfigurationProperties({DataSourceProperties.class, InfluxProperties.class, ...})
                                // 일일이 설정 클래스를 지정 해야 하는 번거로움을 없애기 위해 (springboot 2.2부터 지원)
public class MycarshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(MycarshopApplication.class, args);
    }

}
