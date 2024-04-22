package org.arg.app.data;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * A Class that holds methods for calculating the nth fibinacci value
 */
@Component
public class FibonacciCalc {

    /**
     * Calculates the nth fibonacci number using a simple iterative algorithm.
     *
     * @param n The Fibonacci number to calculate
     * @return The nth Fibonacci number
     * @throws IllegalArgumentException Throws exception if n is negative.
     */
    public BigInteger fibonacciCalc(Long n) throws IllegalArgumentException {

        if (n < 0) throw new IllegalArgumentException("fibinacciCalc passed negative n value");

        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;

        for (int i = 0; i < n; i++) {
            BigInteger temp = a;
            a = b;
            b = b.add(temp);
        }

        return a;
    }
}


