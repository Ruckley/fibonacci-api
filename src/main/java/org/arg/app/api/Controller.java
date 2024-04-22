package org.arg.app.api;

import org.arg.app.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

/**
 * The Fibonaci API controller class. Handles api requests.
 */
@RestController
public class Controller {

    private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());

    @Autowired
    Config config;
    @Autowired
    private FibonacciService service;

    /**
     * A health check route for use by Kubernetes and general health checking purposes.
     * @return 200 response
     */
    @GetMapping("/health_check")
    public Mono<ResponseEntity<String>> healthCheck() {
        return Mono.just(ResponseEntity.ok().build());
    }

    /**
     * The Fibonacci route for the API.
     * Any input for n that cannot be converted to a Long will be rejected with a 400 Bade Request response.
     * A negative n will return an error message.
     * @param n The Fibonacci number to be returned.
     * @return The nth Fibonacci number.
     */
    @GetMapping("/fib")
    public Mono<String> fibinacci(@RequestParam(required = true, name = "n") Long n) {

        if(n < 0) return Mono.just("n must be 0 or positive.");

        return service.calculateFibonacci(n)
                .timeout(Duration.ofMillis(config.getTimeoutMillies()))
                .onErrorResume(this::handleError);
    }

    /**
     * A helper method for handling any error created during the execution of a route.
     * Logs the error and returns an explanation to the caller.
     * @param error The error thrown during execution.
     * @return Mono of error explanation.
     */
    private Mono<String> handleError(Throwable error) {
        if (error instanceof TimeoutException) {
            return Mono.just("Timeout occurred while calculating Fibonacci. Time limit: " + config.getTimeoutMillies() + "ms");
        } else {
            LOGGER.severe("Unexpected Error: " + error);
            return Mono.just("Unexpected Error Occurred");
        }
    }

}
