package org.arg.app.api;

import org.arg.app.data.FibonacciCalc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * This class is the service layer for the Fibonacci API. It is responsible for translating api requests to data requests
 * and the result of those requests into reactive streams.
 */
@Service
public class FibonacciService {

    @Autowired
    FibonacciCalc fibCalc;

    /**
     * Finds the nth Fibonacci number in a non-blocking reactive manner.
     * Mono.fromCallable creates reactive stream from the blocking fibonacciCalc to make it asynchronous
     * subscribeOn sets that when this stream is used, execute it on another thread.
     * Schedulers.boundedElastic allows the stream to create threads up to a certain bound.
     *
     * @param n The Fibonacci number to calculate.
     * @return Mono of the nth Fibonacci number.
     */
    public Mono<String> calculateFibonacci(Long n) {
        return Mono.fromCallable(
                        () -> fibCalc.fibonacciCalc(n).toString()
                )
                .subscribeOn(Schedulers.boundedElastic());
    }

}
