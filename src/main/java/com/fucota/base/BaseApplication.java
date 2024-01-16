package com.fucota.base;

import com.fucota.base.core.LanguageConfigMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaseApplication {

    public static void main(String[] args) {
        LanguageConfigMessage.initMap();
        SpringApplication.run(BaseApplication.class, args);
    }

}
