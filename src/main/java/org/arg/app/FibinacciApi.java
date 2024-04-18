package org.arg.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
@ComponentScan(basePackages = "org.arg.app")
@EnableConfigurationProperties(Config.class)
public class FibinacciApi {

    public static void main(String[] args) {
        SpringApplication.run(FibinacciApi.class, args);
    }

}