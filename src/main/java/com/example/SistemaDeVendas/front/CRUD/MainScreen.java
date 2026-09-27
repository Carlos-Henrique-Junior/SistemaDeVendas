package com.example.SistemaDeVendas.front.CRUD;


import com.example.SistemaDeVendas.entidades.Produto;
import com.example.SistemaDeVendas.front.CRUD.CREATE.App;
import com.example.SistemaDeVendas.front.CRUD.RUD.RUDScreen;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.example.SistemaDeVendas.front.CRUD.CREATE.App.trataImagem;

public class MainScreen {
    private JPanel mainScreen;
    private JButton fazerUmPedidoButton;
    private JButton verUmPedidoButton;
    private JLabel labelLogoWolffsOficial;
    private JLabel primeiroTexto;
    private JLabel instagramLabel;
    private JLabel segueLaLabel;
    private App app;
    private RUDScreen readScreen;

    public JPanel getMainScreen() {
        return mainScreen;
    }

    public MainScreen(App app, RUDScreen readScreen) {
        this.app = app;
        this.readScreen = readScreen;
        fazerUmPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("App");
                frame.setContentPane(app.getMainScreen());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(1500, 400);
                frame.setVisible(true);
            }
        });
        verUmPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Tela de Pedidos");
                frame.setContentPane(readScreen.getReadScreen());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(1500, 400);
                frame.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        // Texto de inicio
        primeiroTexto = new JLabel();
        primeiroTexto.setFont(new Font("Arial", Font.BOLD, 26));

        // Fotinho da tela
        ImageIcon IconMerchant = new ImageIcon("assets/ETIQUETA 1 WOLFF'S-1.png");
        labelLogoWolffsOficial = new JLabel(trataImagem(IconMerchant));

        //insta
        instagramLabel = new JLabel();
        instagramLabel.setFont(new Font("Arial", Font.BOLD, 35));

        //seguelá
        segueLaLabel = new JLabel();
        segueLaLabel.setFont(new Font("Arial", Font.BOLD, 26));

        // Configurações do Hibernate
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-vendas");
        EntityManager em = emf.createEntityManager();
        try {
            //setups da screen de create
            Produto produtoBiscoito = em.find(Produto.class, 1);
            Produto produtoPipoca = em.find(Produto.class, 2);
            Produto produtoBrownie = em.find(Produto.class, 3);
            app.montaProdutoNaTela(produtoBiscoito,app);
            app.montaProdutoNaTelaB(produtoPipoca,app);
            app.montaProdutoNaTelaC(produtoBrownie,app);
        } finally {
            em.close();
            emf.close();
        }
    }
}
