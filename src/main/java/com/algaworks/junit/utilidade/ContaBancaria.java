package com.algaworks.junit.utilidade;

import java.math.BigDecimal;

public final class ContaBancaria {

    private BigDecimal saldo;

    public ContaBancaria(BigDecimal saldo) {
        if (saldo == null) {
            throw new IllegalArgumentException("Saldo não pode ser nulo");
        }
        this.saldo = saldo;
    }

    public void saque(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor igual a zero ou menor é inválido");
        }

        if (valor.compareTo(saldo()) > 0) {
            throw new RuntimeException("Valor de saldo insuficiente");
        }
        this.saldo = saldo().subtract(valor);
    }

    public void deposito(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor igual a zero ou menor é inválido");
        }
        this.saldo = saldo().add(valor);
    }

    public BigDecimal saldo() {
        return this.saldo;
    }
}
