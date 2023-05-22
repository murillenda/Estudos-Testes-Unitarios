package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) // Faz a substituição do undescore na subclasse ReplaceUnderscors
class FiltroNumerosTest {

    @Test
    void Dado_uma_lista_de_numeros_Quando_filtrar_por_pares_Entao_deve_retornar_apenas_numeros_pares() {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4);
        List<Integer> numerosParesEsperados = Arrays.asList(2, 4);
        List<Integer> resultadoFiltro = FiltroNumeros.numerosPares(numeros);

        // Faz a verificação tanto do conteúdo quanto da ordem
        // Usa o método equals do próprio objeto (Precisa estar com equals implementado para funcionar corretamente)
        // Usado para Collections
        assertIterableEquals(numerosParesEsperados, resultadoFiltro);

        // Usado para arrays, no geral igual ao iterable
        assertArrayEquals(numerosParesEsperados.toArray(new Object[]{}), resultadoFiltro.toArray(new Object[]{}));
    }

    @Test
    void Dado_uma_lista_de_numeros_Quando_filtrar_por_impares_Entao_deve_retornar_apenas_numeros_impares() {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4);
        List<Integer> numerosImparesEsperados = Arrays.asList(1, 3);
        List<Integer> resultadoFiltro = FiltroNumeros.numerosImpares(numeros);

        assertIterableEquals(numerosImparesEsperados, resultadoFiltro);
    }

}