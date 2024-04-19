package org.arg.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigInteger;
import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ApiTest {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {
        webTestClient = webTestClient.mutate()
                // This makes sure the timeout of the test webclient is longer than the api timeout
                .responseTimeout(Duration.ofMillis(3000))
                .build();
    }

    @Test
    void canHandleFibRequest() {
        webTestClient.get().uri("/fib?n=3")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class).isEqualTo("2");
    }

    @Test
    void canHandleFibRequestWithLargeN() {
        webTestClient.get().uri("/fib?n=2000")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("4224696333392304878706725602341482782579852840250681098010280137314308584370130707224123599639141511088446087538909603607640194711643596029271983312598737326253555802606991585915229492453904998722256795316982874482472992263901833716778060607011615497886719879858311468870876264597369086722884023654422295243347964480139515349562972087652656069529806499841977448720155612802665404554171717881930324025204312082516817125");
    }

    @Test
    void canHandleNegativeN() {
        webTestClient.get().uri("/fib?n=-1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("n must be 0 or positive.");


    }

    @Test
    void timeOutOnLongRunningFibRequest() {
        webTestClient.get().uri("/fib?n=99999999999")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Timeout occurred while calculating Fibonacci.");


    }

}
