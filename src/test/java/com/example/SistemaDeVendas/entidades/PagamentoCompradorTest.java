package com.example.SistemaDeVendas.entidades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class PagamentoCompradorTest {

    @Test
    void construtorDePagamentoRegistraHoraETipo() {
        Pedido pedido = new Pedido(null, "Rua das Flores, 123", null);

        Pagamento pagamento = new Pagamento(null, "PIX", pedido);

        assertEquals("PIX", pagamento.getTipo());
        assertNotNull(pagamento.getHoraPagamento());
        assertSame(pedido, pagamento.getPedido());
        assertEquals("PIX", pagamento.toString());
    }

    @Test
    void compradorToStringRetornaApenasONome() {
        Comprador comprador = new Comprador(1, 999999999, "Carlos", "carlos@example.com");

        assertEquals("Carlos", comprador.toString());
        assertEquals("carlos@example.com", comprador.getEmail());
    }
}
