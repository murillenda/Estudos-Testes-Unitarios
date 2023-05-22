package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ContaBancariaTest {

    @Test
    void saqueComValorValido() {
        var conta = new ContaBancaria(BigDecimal.TEN);
        var valorValido = new BigDecimal("5");
        var valorSaldoAposSaque = conta.saldo().subtract(valorValido);

        conta.saque(valorValido);
        assertEquals(conta.saldo(), valorSaldoAposSaque);
    }

    @Test
    void saqueComValorIgualValido() {
        var conta = new ContaBancaria(BigDecimal.TEN);
        var valorValido = BigDecimal.TEN;
        var valorSaldoAposSaque = conta.saldo().subtract(valorValido);

        conta.saque(valorValido);
        assertEquals(conta.saldo(), valorSaldoAposSaque);
    }

    @Test
    void saqueComValorZeroFalha() {
        var contaSaldoZerado = new ContaBancaria(BigDecimal.TEN);
        var valorInvalidoZero = BigDecimal.ZERO;
        assertThrows(
                IllegalArgumentException.class,
                () -> contaSaldoZerado.saque(valorInvalidoZero),
                "Valor igual a zero ou menor é inválido");
    }

    @Test
    void saqueComValorNegativoFalha() {
        var contaSaldoZerado = new ContaBancaria(BigDecimal.TEN);
        var valorInvalidoNegativo = new BigDecimal("-5");
        assertThrows(
                IllegalArgumentException.class,
                () -> contaSaldoZerado.saque(valorInvalidoNegativo),
                "\"Valor igual a zero ou menor é inválido\"");
    }

    @Test
    void saqueComSaldoInsuficienteFalha() {
        var contaSaldoZerado = new ContaBancaria(BigDecimal.ZERO);
        var valorValido = BigDecimal.TEN;
        assertThrows(
                RuntimeException.class,
                () -> contaSaldoZerado.saque(valorValido),
                "Valor de saldo insuficiente");
    }

    @Test
    void saqueComValorNullFalha() {
        var conta = new ContaBancaria(BigDecimal.TEN);
        assertThrows(IllegalArgumentException.class, () -> conta.saque(null));
    }

    @Test
    void depositoComValorValido() {
        var conta = new ContaBancaria(BigDecimal.TEN);
        var valorValido = BigDecimal.TEN;
        var valorSaldoAposDeposito = conta.saldo().add(valorValido);

        conta.deposito(valorValido);
        assertEquals(conta.saldo(), valorSaldoAposDeposito);
    }

    @Test
    void depositoComValorNuloFalha() {
        var conta = new ContaBancaria(BigDecimal.TEN);
        assertThrows(
                IllegalArgumentException.class,
                () -> conta.deposito(null),
                "Valor igual a zero ou menor é inválido");
    }

    @Test
    void depositoComValorZeroFalha() {
        var conta = new ContaBancaria(BigDecimal.TEN);
        var valorInvalidoZero = BigDecimal.ZERO;
        assertThrows(
                IllegalArgumentException.class,
                () -> conta.deposito(valorInvalidoZero),
                "Valor igual a zero ou menor é inválido");
    }

    @Test
    void depositoComValorNegativoFalha() {
        var conta = new ContaBancaria(BigDecimal.TEN);
        var valorInvalidoNegativo = new BigDecimal("-10");
        assertThrows(
                IllegalArgumentException.class,
                () -> conta.deposito(valorInvalidoNegativo),
                "Valor igual a zero ou menor é inválido");
    }

    @Test
    void saqueAposDeposito() {
        ContaBancaria conta = new ContaBancaria(BigDecimal.TEN);
        conta.deposito(BigDecimal.TEN);

        var valorSaldoAposSaque = conta.saldo().subtract(BigDecimal.ONE);
        conta.saque(BigDecimal.ONE);

        assertEquals(conta.saldo(), valorSaldoAposSaque);
    }

    @Test
    void saldo() {
        ContaBancaria conta = new ContaBancaria(new BigDecimal("29.03"));
        assertEquals(new BigDecimal("29.03"), conta.saldo());
    }

    @Test
    void criarContaSaldoNullFalha() {
        assertThrows(IllegalArgumentException.class, () -> new ContaBancaria(null));
    }
}