package com.example.SistemaDeVendas.front.CRUD.CREATE;

import com.example.SistemaDeVendas.DAO.DAO;
import com.example.SistemaDeVendas.entidades.Comprador;
import com.example.SistemaDeVendas.entidades.Pagamento;
import com.example.SistemaDeVendas.entidades.Pedido;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Carrinho {
    private JTextField nomeField;
    private JTextField celularField;
    private JTextField emailField;
    private JTextField enderecoField;
    private JComboBox pagamentoEscolha;
    private JLabel totalPedido;
    private JButton finalizarPedidoButton;
    private JPanel carrinhoTela;
    private Pedido pedido;
    private Comprador comprador;
    private Pagamento pagamento;

    public JPanel getCarrinhoTela() {
        return carrinhoTela;
    }

    public Carrinho() {
        //
        DAO<Pedido> daoPedido = new DAO<>(Pedido.class);
        Pedido pedidoPraDarGetTotal = daoPedido.createCustomQUERY("SELECT p FROM Pedido p ORDER BY p.idPedido DESC");
        totalPedido.setText(String.valueOf(pedidoPraDarGetTotal.getTotalPedido()));
        // BOTÃO PARA FINALIZAR PEDIDO
        finalizarPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica de criar um comprador e persistir no db
                comprador = new Comprador(null,
                        Integer.parseInt(celularField.getText()),
                        nomeField.getText(),
                        emailField.getText());

                DAO<Comprador> daoDeComprador = new DAO<>(Comprador.class);
                daoDeComprador.persisteNoDB(comprador);

                // recebe o pedido criado na tela anterior e da set de informações que faltavam
                DAO<Pedido> daoDePedido = new DAO<>(Pedido.class); // DAO de pedido pra manipular banco
                pedido = daoDePedido.createCustomQUERY("SELECT p FROM Pedido p ORDER BY p.id DESC");
                pedido.setLocalDeEntrega(enderecoField.getText());
                pedido.setComprador(comprador);

                // Lógica de criar um pagamento e persistir no db
                pagamento = new Pagamento(null,
                           String.valueOf(pagamentoEscolha.getSelectedItem())
                            ,pedido);

                pedido.setPagamento(pagamento);
                daoDePedido.update(pedido);
            }
        });

    }

    private void createUIComponents() {
        // Instanciação dos widgets usado na Jframe de carrinho
        nomeField = new JTextField();
        celularField = new JTextField();
        emailField = new JTextField();
        enderecoField = new JTextField();
        finalizarPedidoButton = new JButton();
        totalPedido = new JLabel();

        String[] pagamentosTipos = {"Boleto","Cartão de Crédito", "PIX"};
        pagamentoEscolha = new JComboBox<>(pagamentosTipos);

    }
}
