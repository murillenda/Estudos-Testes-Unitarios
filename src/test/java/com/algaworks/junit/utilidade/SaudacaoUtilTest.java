package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class SaudacaoUtilTest {

    private static final String SAUCADAO_INCORRETA = "Saudação incorreta!";

    @Test
    void deveSaudarBomDia() {
        int horaValida = 9;
        String saudacao = SaudacaoUtil.saudar(horaValida);
        assertEquals("Bom dia", saudacao, SAUCADAO_INCORRETA);
    }

    @Test
    void deveSaudarBomDiaAPartir5h() {
        int horaValida = 5;
        String saudacao = SaudacaoUtil.saudar(horaValida);
        assertEquals("Bom dia", saudacao, SAUCADAO_INCORRETA);
    }

    @Test
    void deveSaudarBoaTarde() {
        int horaValida = 14;
        String saudacao = SaudacaoUtil.saudar(horaValida);
        assertEquals("Boa tarde", saudacao, SAUCADAO_INCORRETA);
    }

    @Test
    void deveSaudarBoaNoite() {
        int horaValida = 20;
        String saudacao = SaudacaoUtil.saudar(horaValida);
        assertEquals("Boa noite", saudacao, SAUCADAO_INCORRETA);
    }

    @Test
    void deveSaudarBoaNoiteAs4h() {
        int horaValida = 4;
        String saudacao = SaudacaoUtil.saudar(horaValida);
        assertEquals("Boa noite", saudacao, SAUCADAO_INCORRETA);
    }

    @Test
    void deveLancarException() {
        int horaInvalida = -10;
        // Executable do Jupiter
        Executable chamadaInvalidaDeMetodo = () -> SaudacaoUtil.saudar(horaInvalida);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, chamadaInvalidaDeMetodo);
        assertEquals("Hora inválida", e.getMessage());
    }

    @Test
    void naoDeveLancarException() {
        int horaValida = 0;
        Executable chamadaValidaDeMetodo = () -> SaudacaoUtil.saudar(horaValida);
        assertDoesNotThrow(chamadaValidaDeMetodo);
    }

}