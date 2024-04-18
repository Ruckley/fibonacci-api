package org.arg.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = FibonacciCalc.class)
public class FibonacciCalcTest {

    @Autowired
    FibonacciCalc calc;



    @Test
    void fibonacciCalcHandlesZero(){
        assertEquals(BigInteger.ZERO, calc.fibonacciCalc(0L));
    }

    @Test
    void fibonacciCalcHandlesOne(){
        assertEquals(BigInteger.ONE, calc.fibonacciCalc(1L));
    }

    @Test
    void fibonacciCalcHandlesTwo(){
        assertEquals(BigInteger.valueOf(1), calc.fibonacciCalc(2L));
    }

    @Test
    void fibonacciCalcHandles3Plus(){
        assertEquals(BigInteger.valueOf(2), calc.fibonacciCalc(3L));
        assertEquals(BigInteger.valueOf(3), calc.fibonacciCalc(4L));
        assertEquals(BigInteger.valueOf(5), calc.fibonacciCalc(5L));
        assertEquals(BigInteger.valueOf(8), calc.fibonacciCalc(6L));
    }

    @Test
    void fibonacciCalcHandles5(){
        assertEquals(BigInteger.valueOf(5), calc.fibonacciCalc(5L));
    }

    @Test
    void fibonacciCalcHandlesSmallN(){
        assertEquals(BigInteger.valueOf(498454011879264L), calc.fibonacciCalc(72L));
    }

    @Test
    void fibonacciCalcHandlesVeryLargeN(){
        assertEquals(
                new BigInteger("4224696333392304878706725602341482782579852840250681098010280137314308584370130707224123599639141511088446087538909603607640194711643596029271983312598737326253555802606991585915229492453904998722256795316982874482472992263901833716778060607011615497886719879858311468870876264597369086722884023654422295243347964480139515349562972087652656069529806499841977448720155612802665404554171717881930324025204312082516817125"),
                calc.fibonacciCalc(2000L)
        );
    }

    @Test
    void fibonacciCalcThrowsErrorOnNegativeN(){
        assertThrows(IllegalArgumentException.class, () -> {calc.fibonacciCalc(-1L);});
    }


}
