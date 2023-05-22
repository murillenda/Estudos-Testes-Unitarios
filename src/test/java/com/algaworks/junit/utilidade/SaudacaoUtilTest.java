package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes no utilitário saudação")
class SaudacaoUtilTest {

    private static final String SAUCADAO_INCORRETA = "Saudação incorreta!";

    @Test
    @DisplayName("Deve saudar com bom dia")
    void deveSaudarBomDia() {
        int horaValida = 9;
        String saudacao = SaudacaoUtil.saudar(horaValida);
        assertEquals("Bom dia", saudacao, SAUCADAO_INCORRETA);
    }

    @Test
    @DisplayName("Deve saudar com bom dia às 5 horas")
    void deveSaudarBomDiaAPartir5h() {
        int horaValida = 5;
        String saudacao = SaudacaoUtil.saudar(horaValida);
        assertEquals("Bom dia", saudacao, SAUCADAO_INCORRETA);
    }

    @Test
    @DisplayName("Deve saudar com boa tarde")
    void deveSaudarBoaTarde() {
        int horaValida = 14;
        String saudacao = SaudacaoUtil.saudar(horaValida);
        assertEquals("Boa tarde", saudacao, SAUCADAO_INCORRETA);
    }

    @Test
    @DisplayName("Deve saudar com boa noite")
    void deveSaudarBoaNoite() {
        int horaValida = 20;
        String saudacao = SaudacaoUtil.saudar(horaValida);
        assertEquals("Boa noite", saudacao, SAUCADAO_INCORRETA);
    }

    @Test
    @DisplayName("Deve saudar com boa noite às 4 horas")
    void deveSaudarBoaNoiteAs4h() {
        int horaValida = 4;
        String saudacao = SaudacaoUtil.saudar(horaValida);
        assertEquals("Boa noite", saudacao, SAUCADAO_INCORRETA);
    }

    @Test
    @DisplayName("Deve lançar exception caso hora inválida")
    void deveLancarException() {
        int horaInvalida = -10;
        // Executable do Jupiter
        Executable chamadaInvalidaDeMetodo = () -> SaudacaoUtil.saudar(horaInvalida);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, chamadaInvalidaDeMetodo);
        assertEquals("Hora inválida", e.getMessage());
    }

    @Test
    @DisplayName("Deve ser um sucesso a requisição")
    void naoDeveLancarException() {
        int horaValida = 0;
        Executable chamadaValidaDeMetodo = () -> SaudacaoUtil.saudar(horaValida);
        assertDoesNotThrow(chamadaValidaDeMetodo);
    }

}