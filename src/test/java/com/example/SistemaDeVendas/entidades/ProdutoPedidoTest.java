package com.example.SistemaDeVendas.entidades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class ProdutoPedidoTest {

    @Test
    void getSubtotalMultiplicaPrecoPelaQuantidade() {
        Pedido pedido = new Pedido(null, "Rua das Flores, 123", null);
        Produto produto = new Produto(1, 0, "Biscoito Decorado", "Baunilha", 18.0f);

        ProdutoPedido produtoPedido = new ProdutoPedido(pedido, produto, 3);

        assertEquals(54.0f, produtoPedido.getSubtotal(), 0.0001f);
        assertEquals(3, produtoPedido.getQuantidade());
    }

    @Test
    void construtorCapturaPrecoDoProdutoNoMomentoDaCriacao() {
        Pedido pedido = new Pedido(null, "Rua das Flores, 123", null);
        Produto produto = new Produto(1, 0, "Pipoca Gourmet", "Pão de Alho", 8.0f);

        ProdutoPedido produtoPedido = new ProdutoPedido(pedido, produto, 2);
        produto.setPreco(99.0f);

        assertEquals(8.0f, produtoPedido.getPreco(), 0.0001f);
        assertEquals(16.0f, produtoPedido.getSubtotal(), 0.0001f);
    }

    @Test
    void getPedidoEGetProdutoDelegamParaAChaveComposta() {
        Pedido pedido = new Pedido(null, "Rua das Flores, 123", null);
        Produto produto = new Produto(2, 0, "Pipoca Gourmet", "Pão de Alho", 8.0f);

        ProdutoPedido produtoPedido = new ProdutoPedido(pedido, produto, 1);

        assertSame(pedido, produtoPedido.getPedido());
        assertSame(produto, produtoPedido.getProduto());
    }

    @Test
    void pedidoDefaultNaoReferenciaPedidoNemProduto() {
        ProdutoPedido produtoPedido = new ProdutoPedido();

        assertNull(produtoPedido.getPedido());
        assertNull(produtoPedido.getProduto());
        assertEquals(0.0f, produtoPedido.getSubtotal(), 0.0001f);
    }
}
