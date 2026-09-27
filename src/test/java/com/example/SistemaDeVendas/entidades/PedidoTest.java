package com.example.SistemaDeVendas.entidades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PedidoTest {

    private Produto produto(String nome, float preco) {
        return new Produto(null, 0, nome, "Baunilha", preco);
    }

    @Test
    void construtorComArgumentosRegistraHoraEPreservaLocalDeEntrega() {
        Pedido pedido = new Pedido(null, "Rua das Flores, 123", null);

        assertEquals("Rua das Flores, 123", pedido.getLocalDeEntrega());
        assertNotNull(pedido.getHoraPedido());
    }

    @Test
    void adicionarProdutoAcumulaSubtotaisNoTotalPedido() {
        Pedido pedido = new Pedido(null, "Rua das Flores, 123", null);

        pedido.adicionarProduto(new ProdutoPedido(pedido, produto("Biscoito", 18.0f), 2));
        pedido.adicionarProduto(new ProdutoPedido(pedido, produto("Pipoca", 8.0f), 3));

        assertEquals(2 * 18.0f + 3 * 8.0f, pedido.getTotalPedido(), 0.0001f);
    }

    @Test
    void getTotalSomaSubtotaisDoConjuntoDeProdutos() {
        Pedido pedido = new Pedido(null, "Rua das Flores, 123", null);

        pedido.adicionarProduto(new ProdutoPedido(pedido, produto("Biscoito", 18.0f), 1));
        pedido.adicionarProduto(new ProdutoPedido(pedido, produto("Brownie", 30.0f), 2));

        assertEquals(18.0f + 60.0f, pedido.getTotal(), 0.0001f);
    }

    @Test
    void pedidoSemProdutosTemTotalZero() {
        Pedido pedido = new Pedido(null, "Rua das Flores, 123", null);

        assertEquals(0.0f, pedido.getTotal(), 0.0001f);
        assertEquals(0.0f, pedido.getTotalPedido(), 0.0001f);
    }
}
