package org.arg.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

@RestController
public class Controller {

    @Autowired
    Config config;
    @Autowired
    private FibonacciService service;

    @GetMapping("/health_check")
    public Mono<ResponseEntity<String>> healthCheck() {
        return Mono.just(ResponseEntity.ok().build());
    }

    @GetMapping("/fib")
    public Mono<String> fibinacci(@RequestParam(required = true, name = "n") Long n) {

        if(n < 0) return Mono.just("n must be 0 or positive.");

        return service.calculateFibonacci(n)
                .timeout(Duration.ofMillis(20/*config.getTimeoutMillies()*/))
                .onErrorResume(this::handleError);
    }

    private Mono<String> handleError(Throwable error) {
        if (error instanceof TimeoutException) {
            return Mono.just("Timeout occurred while calculating Fibonacci.");
        } else {
            return Mono.just("Unexpected Error Occurred");
        }
    }

}
