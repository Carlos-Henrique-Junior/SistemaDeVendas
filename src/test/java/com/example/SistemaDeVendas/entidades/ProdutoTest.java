package com.example.SistemaDeVendas.entidades;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ProdutoTest {

    @Test
    void construtorDeCopiaPreservaTodosOsCampos() {
        Produto original = new Produto(1, 0, "Biscoito Decorado", "Baunilha", 18.0f);

        Produto copia = new Produto(original);

        assertEquals(original.getIdProduto(), copia.getIdProduto());
        assertEquals(original.getNome(), copia.getNome());
        assertEquals(original.getSabor(), copia.getSabor());
        assertEquals(original.getPreco(), copia.getPreco(), 0.0001f);
    }

    @Test
    void getPedidosRetornaOsPedidosVinculadosAosProdutoPedidos() {
        Pedido pedidoA = new Pedido(1, "Rua A", null);
        Pedido pedidoB = new Pedido(2, "Rua B", null);
        Produto produto = new Produto(1, 0, "Biscoito Decorado", "Baunilha", 18.0f);

        produto.setPedidoList(Set.of(
                new ProdutoPedido(pedidoA, produto, 1),
                new ProdutoPedido(pedidoB, produto, 2)));

        Set<Pedido> pedidos = produto.getPedidos();

        assertEquals(2, pedidos.size());
        assertSame(pedidoA, pedidos.stream().filter(p -> p.getIdPedido() == 1).findFirst().orElseThrow());
        assertSame(pedidoB, pedidos.stream().filter(p -> p.getIdPedido() == 2).findFirst().orElseThrow());
    }
}
