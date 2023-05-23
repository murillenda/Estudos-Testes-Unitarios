package com.algaworks.junit.ecommerce;

import com.algaworks.junit.ecommerce.exception.ItemNaoEncontradoException;

import java.math.BigDecimal;
import java.util.*;

public class CarrinhoCompra {

	public static final String PRODUTO_NAO_PODE_SER_NULO_MESSAGE = "Produto não pode ser nulo!";
	
	private final Cliente cliente;
	private final List<ItemCarrinhoCompra> itens;

	public CarrinhoCompra(Cliente cliente) {
		this(cliente, new ArrayList<>());
	}

	public CarrinhoCompra(Cliente cliente, List<ItemCarrinhoCompra> itens) {
		Objects.requireNonNull(cliente);
		Objects.requireNonNull(itens);
		this.cliente = cliente;
		this.itens = new ArrayList<>(itens); //Cria lista caso passem uma imutável
	}

	public List<ItemCarrinhoCompra> getItens() {
		return new ArrayList<>(itens);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void adicionarProduto(Produto produto, int quantidade) {
		Objects.requireNonNull(produto, PRODUTO_NAO_PODE_SER_NULO_MESSAGE);
		validarQuantidade(quantidade);
		encontrarItemPeloProduto(produto)
				.ifPresentOrElse(
						item -> item.adicionarQuantidade(quantidade),
						() -> this.itens.add(new ItemCarrinhoCompra(produto, quantidade)));
	}

	public void removerProduto(Produto produto) {
		Objects.requireNonNull(produto, PRODUTO_NAO_PODE_SER_NULO_MESSAGE);
		encontrarItemPeloProduto(produto)
				.ifPresentOrElse(
						this.itens::remove,
						() -> { throw new ItemNaoEncontradoException(produto.getNome() + " não encontrado"); });
	}

	public void aumentarQuantidadeProduto(Produto produto) {
		Objects.requireNonNull(produto, PRODUTO_NAO_PODE_SER_NULO_MESSAGE);
		encontrarItemPeloProduto(produto)
				.ifPresentOrElse(
						item -> item.adicionarQuantidade(1),
						() -> { throw new ItemNaoEncontradoException(produto.getNome() + " não encontrado"); });
	}

    public void diminuirQuantidadeProduto(Produto produto) {
		Objects.requireNonNull(produto, PRODUTO_NAO_PODE_SER_NULO_MESSAGE);
		ItemCarrinhoCompra item = encontrarItemPeloProduto(produto)
				.orElseThrow(RuntimeException::new);
		if (item.getQuantidade() == 1) {
			itens.remove(item);
		} else {
			item.subtrairQuantidade(1);
		}
	}

    public BigDecimal getValorTotal() {
		return itens.stream()
				.map(ItemCarrinhoCompra::getValorTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
    }

	public int getQuantidadeTotalDeProdutos() {
		return itens.stream()
				.mapToInt(ItemCarrinhoCompra::getQuantidade)
				.sum();
	}

	public void esvaziar() {
		itens.clear();
	}

	private void validarQuantidade(int quantidade) {
		if (quantidade <= 0) {
			throw new IllegalArgumentException("Quantidade não pode ser menor que 1");
		}
	}

	private Optional<ItemCarrinhoCompra> encontrarItemPeloProduto(Produto produto) {
		return this.itens.stream()
				.filter(item -> item.getProduto().equals(produto))
				.findFirst();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CarrinhoCompra that = (CarrinhoCompra) o;
		return Objects.equals(itens, that.itens) && Objects.equals(cliente, that.cliente);
	}

	@Override
	public int hashCode() {
		return Objects.hash(itens, cliente);
	}
}