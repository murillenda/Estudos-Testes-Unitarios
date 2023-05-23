package com.algaworks.junit.ecommerce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CarrinhoCompra")
class CarrinhoCompraTest {

    private CarrinhoCompra carrinho;
    private Cliente cliente;
    private List<ItemCarrinhoCompra> itens;
    private Produto picanha;
    private Produto cerveja;

    @BeforeEach
    void beforeEach() {
        cliente = new Cliente(1L, "Murillo");

        picanha = new Produto(1L, "Picanha", "Picanha da braba", new BigDecimal("50.23"));
        cerveja = new Produto(2L, "Cerveja", "Heineken da boa", new BigDecimal("9"));

        itens = new ArrayList<>();
    }

    @Nested
    @DisplayName("Dado um carrinho com 2 itens (Duas picanhas e uma cerveja)")
    class CarrinhoComDoisItensComQuantidadesDiferentes {

        @BeforeEach
        void beforeEach() {
            itens.add(new ItemCarrinhoCompra(picanha, 2));
            itens.add(new ItemCarrinhoCompra(cerveja, 1));

            carrinho = new CarrinhoCompra(cliente, itens);
        }

        @Nested
        @DisplayName("Quando retornar itens")
        class QuandoRetornarItens {

            @Test
            @DisplayName("Então deve retornar dois itens")
            void entaoDeveRetornarDoisItens() {
                assertEquals(2, carrinho.getItens().size());
            }

            @Test
            @DisplayName("E deve retornar uma nova instância da lista de itens")
            void eDeveRetornarUmaNovaLista() {
                carrinho.getItens().clear(); // Get itens, retorna uma nova lista
                assertEquals(2, carrinho.getItens().size()); // Lista do carrinho não foi alterada
            }
        }


        @Nested
        @DisplayName("Quando remover um item")
        class QuandoRemoverUmItem {

            @BeforeEach
            void beforeEach() {
                carrinho.removerProduto(picanha);
            }

            @Test
            @DisplayName("Então deve diminuir a quantidade total de itens")
            void entaoDeveDiminuirQuantidadeTotal() {
                assertEquals(1, carrinho.getItens().size());
            }

            @Test
            @DisplayName("E não deve remover demais itens")
            void naoDeveRemoverDemaisItens() {
                assertEquals(cerveja, carrinho.getItens().get(0).getProduto());
            }
        }

        @Nested
        @DisplayName("Quando aumentar quantidade de um item")
        class QuandoAumentarQuantidade {

            @BeforeEach
            void beforeEach() {
                carrinho.aumentarQuantidadeProduto(picanha);
            }

            @Test
            @DisplayName("Então deve somar na quantidade dos itens iguais")
            void deveSomarNaQuantidade() {
                assertEquals(3, carrinho.getItens().get(0).getQuantidade());
            }

            @Test
            @DisplayName("Então deve manter os demais itens na quantidade que estavam")
            void deveManterOsDemaisItensSemAlteracaoNaQuantidade() {
                assertEquals(1, carrinho.getItens().get(1).getQuantidade());
            }

            @Test
            @DisplayName("Então deve retornar quatro de quantidade total de itens")
            void deveRetornarQuantidadeTotalItens() {
                assertEquals(4, carrinho.getQuantidadeTotalDeProdutos());
            }

            @Test
            @DisplayName("Então deve retornar valor total correto de itens")
            void deveRetornarValorTotal() {
                assertEquals(new BigDecimal("159.69"), carrinho.getValorTotal());
            }
        }

        @Nested
        @DisplayName("Quando diminuir quantidade de um item")
        class QuandoDiminuirQuantidade {

            @BeforeEach
            void beforeEach() {
                carrinho.diminuirQuantidadeProduto(picanha);
            }

            @Test
            @DisplayName("Então deve diminuir a quantidade dos itens iguais")
            void deveDiminuirNaQuantidade() {
                assertEquals(1, carrinho.getItens().get(0).getQuantidade());
            }

            @Test
            @DisplayName("Então deve manter os demais itens na quantidade que estavam")
            void deveManterOsDemaisItensSemAlteracaoNaQuantidade() {
                assertEquals(1, carrinho.getItens().get(1).getQuantidade());
            }

            @Test
            @DisplayName("Então deve retornar dois de quantidade total de itens")
            void deveRetornarQuantidadeTotalItens() {
                assertEquals(2, carrinho.getQuantidadeTotalDeProdutos());
            }

            @Test
            @DisplayName("Então deve retornar valor total correto de itens")
            void deveRetornarValorTotal() {
                assertEquals(new BigDecimal("59.23"), carrinho.getValorTotal());
            }
        }

        @Nested
        @DisplayName("Quando diminuir a quantidade de um item com apenas um de quantidade")
        class QuandoDiminuirQuantidadeDeItemUnico {

            @BeforeEach
            void beforeEach() {
                carrinho.diminuirQuantidadeProduto(cerveja);
            }

            @Test
            @DisplayName("Então deve remover item")
            void deveRemoverItem(){
                assertNotEquals(carrinho.getItens().get(0).getProduto(), cerveja);
            }
        }

        @Nested
        @DisplayName("Quando adicionar item com quantidade inválida")
        class QuandoAdicionarItemQuantidadeInvalida {

            @Test
            @DisplayName("Então deve lançar exception")
            void entaoDeveFalhar() {
                assertThrows(RuntimeException.class, () -> carrinho.adicionarProduto(picanha, -1));
            }
        }

        @Nested
        @DisplayName("Quando esvaziar carrinho")
        class QuandoEsvaziarCarrinho {

            @BeforeEach
            void beforeEach() {
                carrinho.esvaziar();
            }

            @Test
            @DisplayName("Então carrinho não deve ter mais itens")
            void deveEsvaziarCarrinho() {
                assertEquals(0, carrinho.getItens().size());
            }

            @Test
            @DisplayName("Então quantidade total de produtos deve ser 0")
            void deveRetornarQuantidadeTotalItens() {
                assertEquals(0, carrinho.getQuantidadeTotalDeProdutos());
            }

            @Test
            @DisplayName("Então deve retornar zero de valor total de itens")
            void deveRetornarValorTotalItens() {
                assertEquals(BigDecimal.ZERO, carrinho.getValorTotal());
            }
        }
    }

    @Nested
    @DisplayName("Dado um carrinho vazio")
    class DadoCarrinhoVazio {

        @BeforeEach
        public void beforeEach() {
            itens = new ArrayList<>();
            carrinho = new CarrinhoCompra(cliente, itens);
        }

        @Nested
        @DisplayName("Quando adicionar dois itens iguais")
        class QuandoAdicionarDoisItensIguais {

            @BeforeEach
            void beforeEach() {
                carrinho.adicionarProduto(cerveja, 2);
            }

            @Test
            @DisplayName("Então deve somar na quantidade dos itens iguais")
            void entaoDeveSomarNaQuantidade() {
                assertEquals(2, carrinho.getItens().get(0).getQuantidade());
            }

            @Test
            @DisplayName("E retornar três de quantidade total de itens")
            void eRetornarQuantidadeTotalItens() {
                assertEquals(2, carrinho.getQuantidadeTotalDeProdutos());
            }

            @Test
            @DisplayName("E retornar valor total correto de itens")
            void eRetornarValorTotalItens() {
                assertEquals(new BigDecimal("18"), carrinho.getValorTotal());
            }
        }

    }
}