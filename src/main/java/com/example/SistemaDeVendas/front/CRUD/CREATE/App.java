package com.example.SistemaDeVendas.front.CRUD.CREATE;

import com.example.SistemaDeVendas.DAO.DAO;
import com.example.SistemaDeVendas.entidades.Pedido;
import com.example.SistemaDeVendas.entidades.Produto;
import com.example.SistemaDeVendas.entidades.ProdutoPedido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private JPanel MainScreen;
    private JButton adicionarAoCarrinhoButton;
    private JLabel fotoProdutoA;
    private JLabel fotoProdutoB;
    private JLabel fotoProdutoC;
    private JButton carrinhoDeComprasButton;
    private JLabel labelDoMerchant;
    private JLabel wolffsoficial;
    private JLabel precinho;
    private JLabel produtoANome;
    private JLabel saborGet;
    private JLabel precoGet;
    private JLabel produtoBNome;
    private JLabel precoBGet;
    private JLabel saborBGet;
    private JLabel produtoCNome;
    private JLabel precoCGet;
    private JLabel saborCGet;
    private JTextField quantidadeAField;
    private JTextField quantidadeBField;
    private JTextField quantidadeCField;
    private Pedido pedido;

    public App() {
        carrinhoDeComprasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Carrinho");
                Carrinho carrinho = new Carrinho();
                frame.setContentPane(carrinho.getCarrinhoTela());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(1500, 400);
                frame.setVisible(true);
            }
        });
        adicionarAoCarrinhoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Instanciamos os DAOs que vão ser utilizados, de produto e de pedido
                DAO<Produto> daoProduto = new DAO<>(Produto.class);
                DAO<Pedido> daoDePedido = new DAO<>(Pedido.class);
                DAO<ProdutoPedido> daoDeProdutoPedido = new DAO<>(ProdutoPedido.class);

                // Cria-se o pedido no momento que o cliente clica no botão de adicionar ao carrinho.
                pedido = new Pedido(null,null,null);


                // Produto 1 é buscado do banco de dados e instanciado no programa
                Produto produto = daoProduto.selectNaDBbyID(1);
                ProdutoPedido produtoPedido = new ProdutoPedido(pedido, produto, Integer.parseInt(quantidadeAField.getText()));

                // Produto 2 é buscado do banco de dados e instanciado no programa
                Produto produto2 = daoProduto.selectNaDBbyID(2);
                ProdutoPedido produtoPedido2 = new ProdutoPedido(pedido, produto2, Integer.parseInt(quantidadeBField.getText()));


                // Produto 3 é buscado do banco de dados e instanciado no programa
                Produto produto3 = daoProduto.selectNaDBbyID(3);
                ProdutoPedido produtoPedido3 = new ProdutoPedido(pedido, produto3, Integer.parseInt(quantidadeCField.getText()));


                pedido.adicionarProduto(produtoPedido);
                pedido.adicionarProduto(produtoPedido2);
                pedido.adicionarProduto(produtoPedido3);
                daoDePedido.persisteNoDB(pedido);
                daoDeProdutoPedido.persisteNoDB(produtoPedido);
                daoDeProdutoPedido.persisteNoDB(produtoPedido2);
                daoDeProdutoPedido.persisteNoDB(produtoPedido3);
            }
        });
    }

    public JPanel getMainScreen() {
        return MainScreen;
    }

    private void createUIComponents() {
        //modulozinho do primeiro produto
        produtoANome = new JLabel();

        fotoProdutoA = new JLabel();
        ImageIcon icon = new ImageIcon("assets/BiscoitoDecorado.png");
        fotoProdutoA.setIcon(trataImagem(icon));

        quantidadeAField = new JTextField();
        precoGet = new JLabel();
        saborGet = new JLabel();


        //modulozinho contendo objetos do segundo produto
        produtoBNome = new JLabel();

        fotoProdutoB = new JLabel();
        ImageIcon iconB = new ImageIcon("assets/PipocaGourmet.png");
        fotoProdutoB.setIcon(trataImagem(iconB));

        quantidadeBField = new JTextField();
        precoBGet = new JLabel();
        saborBGet = new JLabel();


        //modulozinho contendo objetos do terceiro produto
        produtoCNome = new JLabel();

        fotoProdutoC = new JLabel();
        ImageIcon iconC = new ImageIcon("assets/Brownie.png");
        fotoProdutoC.setIcon(trataImagem(iconC));

        quantidadeCField = new JTextField();
        precoCGet = new JLabel();
        saborCGet = new JLabel();


        //partezinha do merchant que eu sou esperto né ta ligado
        ImageIcon IconMerchant = new ImageIcon("assets/ETIQUETA 1 WOLFF'S-1.png");
        labelDoMerchant = new JLabel(trataImagem(IconMerchant));
        Font myFont = new Font("Arial", Font.BOLD, 20);
        wolffsoficial = new JLabel();
        precinho = new JLabel();
        wolffsoficial.setFont(myFont);
        precinho.setFont(myFont);
    }

    public static ImageIcon trataImagem(ImageIcon icon) { // Essa classe re-escala a imagem, só usar um setSize do Swing cortava a imagem
        Image imgIconToImgOBJ = icon.getImage();
        Image imagemTratada = imgIconToImgOBJ.getScaledInstance(150,150, Image.SCALE_SMOOTH);
        return new ImageIcon(imagemTratada);
    }

    public void montaProdutoNaTela(Produto produto, App app) {
        app.produtoANome.setText(produto.getNome());
        app.precoGet.setText(String.valueOf(produto.getPreco()));
        app.saborGet.setText(produto.getSabor());
    }
    public void montaProdutoNaTelaB(Produto produto, App app) {
        app.produtoBNome.setText(produto.getNome());
        app.precoBGet.setText(String.valueOf(produto.getPreco()));
        app.saborBGet.setText(produto.getSabor());
    }
    public void montaProdutoNaTelaC(Produto produto, App app) {
        app.produtoCNome.setText(produto.getNome());
        app.precoCGet.setText(String.valueOf(produto.getPreco()));
        app.saborCGet.setText(produto.getSabor());
    }
}
