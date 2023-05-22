package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Test;

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
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> SaudacaoUtil.saudar(-10));
        assertEquals("Hora inválida", illegalArgumentException.getMessage());
    }

    @Test
    void naoDeveLancarException() {
        assertDoesNotThrow(() -> SaudacaoUtil.saudar(0));
    }

}