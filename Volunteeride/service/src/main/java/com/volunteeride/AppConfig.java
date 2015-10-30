package com.volunteeride;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * This class represents spring configuration for Volunteeride App
 *
 * Created by ayazlakdawala on 9/17/2015.
 */
@SpringBootApplication
public class AppConfig extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppConfig.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }

}
