package com.example.SistemaDeVendas.front.CRUD.RUD;

import com.example.SistemaDeVendas.DAO.DAO;
import com.example.SistemaDeVendas.entidades.Pagamento;
import com.example.SistemaDeVendas.entidades.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RUDScreen {
    private JTable tablePedidos;
    private JPanel readScreen;
    private JTextField idField;
    private JButton buscarButton;
    private JScrollPane scrollPane;
    private JButton atualizarButton;
    private JButton deletarPedidoButton;
    private JButton atualizarPagamentoButton;
    private JComboBox pagamentoEscolha;
    private String[] colunas = {
        "ID do Pedido", "Hora do Pedido", "Local de Entrega",
                "Total do Pedido", "Comprador", "Tipo de Pagamento"
    };

    public JPanel getReadScreen() {
        return readScreen;
    }

    public RUDScreen() {

        // botão de buscar por ID
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarByIdTabela(Integer.parseInt(idField.getText()));
            }
        });
        // botão de atualizar a tabela
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarTabela();
            }
        });
        // botão para deletar o pedido
        deletarPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DAO<Pedido> DAOpedido = new DAO<>(Pedido.class);
                DAOpedido.deleteObjectById(Integer.parseInt(idField.getText()));
            }
        });
        // botão mudar um tipo de pagamento de um pedido
        atualizarPagamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DAO<Pedido> DAOPedido = new DAO<>(Pedido.class);
                Pedido pedido = DAOPedido.selectNaDBbyID(Integer.parseInt(idField.getText()));
                Pagamento pagamento = new Pagamento(
                        null,
                        String.valueOf(pagamentoEscolha.getSelectedItem()),
                        pedido);
                pedido.setPagamento(pagamento);
                DAOPedido.update(pedido);
            }
        });
    }

    private void createUIComponents() {
        // Inicialização de widgets
        idField = new JTextField();
        String[] pagamentosTipos = {"Boleto","Cartão de Crédito", "PIX"};
        pagamentoEscolha = new JComboBox<>(pagamentosTipos);

        DAO<Pedido> daoPedido = new DAO<>(Pedido.class);
        List<Pedido> pedidoList = daoPedido.getAllObjects();

        Object[][] dados = montaTabelaDePedidos(pedidoList);

        // Criando o modelo da tabela
        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        tablePedidos = new JTable(model);
        // por alguma razão os headers da tabela não aparecem se tu não faz isso
        scrollPane = new JScrollPane(tablePedidos);
    }

    private void atualizarTabela(){

        DAO<Pedido> daoPedido = new DAO<>(Pedido.class);
        List<Pedido> pedidoList = daoPedido.getAllObjects();

        Object[][] dados = montaTabelaDePedidos(pedidoList);

        // sim aqui a gente cria outro model e joga por cima do anterior
        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        tablePedidos.setModel(model);
    }

    // busca um pedido por ID e mostra ele na table
    private void buscarByIdTabela(Integer idParam){
        DAO<Pedido> daoPedido = new DAO<>(Pedido.class);
        Pedido pedido = daoPedido.selectNaDBbyID(idParam);

        Object[] objPedido = {
                pedido.getIdPedido(),pedido.getHoraPedido(),pedido.getLocalDeEntrega(),
                pedido.getTotalPedido(), pedido.getComprador(), pedido.getPagamento()
        };

        Object[][] dados = {objPedido};

        // cara isso é um absurdo
        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        tablePedidos.setModel(model);
    }

    // lógica pra preencher a tabela
    // eu não sei como os método de .addRow e .addColumns não funcionam e esse trabuco veio a existência
    private Object[][] montaTabelaDePedidos(List<Pedido> pedidoList) {
        Object[][] dados = new Object[pedidoList.size()][colunas.length];

        for (int i = 0; i < pedidoList.size(); i++) {
            Pedido pedido = pedidoList.get(i);
            dados[i][0] = pedido.getIdPedido();
            dados[i][1] = pedido.getHoraPedido();
            dados[i][2] = pedido.getLocalDeEntrega();
            dados[i][3] = pedido.getTotalPedido();
            dados[i][4] = pedido.getComprador();
            dados[i][5] = pedido.getPagamento();
        }

        return dados;
    }
}
