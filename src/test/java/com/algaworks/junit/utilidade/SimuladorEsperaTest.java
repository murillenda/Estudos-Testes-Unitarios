package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class SimuladorEsperaTest {

    @Test
    void deveEsperarENaoDarTimeout() {
        // Método deveria executar em 1 segundo
        // Neste assertTimetout, ele só da erro quando o método termina de rodar
        assertTimeout(Duration.ofSeconds(1), () -> SimuladorEspera.esperar(Duration.ofMillis(10)));

        // Neste assertTimeoutPreemptively quando passa o 1 segundo ele já lança o erro no teste
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> SimuladorEspera.esperar(Duration.ofMillis(10)));

    }
}