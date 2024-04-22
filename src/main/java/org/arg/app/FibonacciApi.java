package org.arg.app;

import org.arg.app.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;


/**
 * Main class for the Fibonacci API
 */
@EnableWebFlux
@SpringBootApplication
@ComponentScan(basePackages = "org.arg.app")
@EnableConfigurationProperties(Config.class)
public class FibonacciApi {

    public static void main(String[] args) {
        SpringApplication.run(FibonacciApi.class, args);
    }

}