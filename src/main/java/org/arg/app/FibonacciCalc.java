package org.arg.app;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class FibonacciCalc {

    // My initial thought was to use tail recursion but the Java compiler cant guarantee that a method will be tail
    // recursive even if written to be so

//    public BigInteger fibonacciCalc(Integer n) {
//        if (n.equals(0)) {
//            return BigInteger.ZERO;
//        } else {
//            return fibonacciCalc(BigInteger.ZERO, BigInteger.ONE, 1, n);
//        }
//    }
//
//    private BigInteger fibonacciCalc(BigInteger last, BigInteger current, Integer count, Integer n) {
//        if (count.equals(n)) {
//            return current;
//        } else {
//            BigInteger newCurrent = last.add(current);
//            return fibonacciCalc(current, newCurrent, count + 1, n);
//        }
//    }

    //Iterative calculation
    public BigInteger fibonacciCalc(Long n) throws IllegalArgumentException{

        if(n < 0) throw new IllegalArgumentException("fibinacciCalc passed negative n value");

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
