package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Conta bancária")
class ContaBancariaTest {

    // Nested diz pro JUnit que nessa innerClass existem testes unitários para serem executados
    @Nested
    @DisplayName("Dado uma conta bancária com saldo de R$ 10,00")
    class ContaBancariaComSaldo {

        private ContaBancaria conta;

        @BeforeEach
        void beforeEach() {
            conta = new ContaBancaria(BigDecimal.TEN);
        }

        @Nested
        @DisplayName("Quando efetuar o saque com o valor menor")
        class SaqueValorMenor {
            private final BigDecimal valorSaque = new BigDecimal("9.0");

            @Test
            @DisplayName("Então não deve lançar exception")
            void naoDeveLancarSaqueSemException() {
                assertDoesNotThrow(() -> conta.saque(valorSaque));
            }

            @Test
            @DisplayName("E deve subtrair do saldo")
            void deveSubtrairDoSaldo() {
                conta.saque(valorSaque);
                assertEquals(new BigDecimal("1.0"), conta.saldo());
            }
        }

        @Nested
        @DisplayName("Quando efetuar o saque com o valor maior")
        class SaqueValorMaior {
            private final BigDecimal valorSaque = new BigDecimal("20.0");

            @Test
            @DisplayName("Então deve lançar exception")
            void deveFalhar() {
                assertThrows(RuntimeException.class, () -> conta.saque(valorSaque));
            }

            @Test
            @DisplayName("E não deve alterar saldo")
            void naoDeveAlterarSaldo() {
                try {
                    conta.saque(valorSaque);
                } catch (Exception ignored) {
                }
                assertEquals(BigDecimal.TEN, conta.saldo());
            }
        }
    }

    @Nested
    @DisplayName("Dado uma conta bancária com saldo de R$ 0,00")
    class ContaBancariaComSaldoZerado {

        private ContaBancaria conta;

        @BeforeEach
        void beforeEach() {
            conta = new ContaBancaria(BigDecimal.ZERO);
        }

        @Nested
        @DisplayName("Quando efetuar o saque com o valor maior")
        class SaqueValorMaior {

            private final BigDecimal valorSaque = new BigDecimal("0.01");

            @Test
            @DisplayName("Então deve lançar exception")
            void deveFalhar() {
                assertThrows(RuntimeException.class, () -> conta.saque(valorSaque));
            }
        }

        @Nested
        @DisplayName("Quando efetuar um depósito de R$ 10,00")
        class DepositoComDezReais {

            private final BigDecimal valorDeposito = new BigDecimal("8.00");

            @Test
            @DisplayName("Então deve somar ao saldo")
            void deveSomarAoSaldo() {
                conta.deposito(valorDeposito);
                assertEquals(new BigDecimal("8.00"), conta.saldo());
            }

        }
    }


}