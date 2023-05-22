package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {

    @Test
    void assercaoAgrupada() {
        Pessoa pessoa = new Pessoa("Murillo", "Marques");

        // AssertAll, mesmo falhando a primeira asserção, ele irá verificar as duas asserções
        // Assim podemos ter mais visibilidade dos testes
        assertAll("Asserções de Pessoa",
                () -> assertEquals("Murillo", pessoa.getNome()),
                () -> assertEquals("Marques", pessoa.getSobrenome()));
    }

}