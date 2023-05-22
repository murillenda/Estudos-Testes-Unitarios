package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class SimuladorEsperaTest {

    @Test
    // @Disabled("Não é mais aplicável")
    // @EnabledIfEnvironmentVariable(named = "ENV", matches = "DEV")
    void deveEsperarENaoDarTimeout() {
        // Assumptions.assumeTrue("PROD".equals(System.getenv("ENV")),
                // () -> "Abortando teste: Não deve ser executado em PROD");
        // Método deveria executar em 1 segundo
        // Neste assertTimetout, ele só da erro quando o método termina de rodar
        assertTimeout(Duration.ofSeconds(1), () -> SimuladorEspera.esperar(Duration.ofMillis(10)));

        // Neste assertTimeoutPreemptively quando passa o 1 segundo ele já lança o erro no teste
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> SimuladorEspera.esperar(Duration.ofMillis(10)));

    }
}