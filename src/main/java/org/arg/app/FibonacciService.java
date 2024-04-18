package org.arg.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


@Service
public class FibonacciService {

    @Autowired
    FibonacciCalc fibCalc;

    public Mono<String> calculateFibonacci(Long n) {
        // Mono.fromCallable creates reactive stream from the blocking fibonacciCalc to make it asynchronous
        // subscribeOn sets that when this stream is used, execute it on another thread
        // Schedulers.boundedElastic allows the stream to create threads up to a certain bound
        return Mono.fromCallable(
                        () -> fibCalc.fibonacciCalc(n).toString()
                )
                .subscribeOn(Schedulers.boundedElastic());
    }

}
