package com.example.SistemaDeVendas;

import com.example.SistemaDeVendas.front.CRUD.CREATE.App;
import com.example.SistemaDeVendas.front.CRUD.MainScreen;
import com.example.SistemaDeVendas.front.CRUD.RUD.RUDScreen;


import javax.swing.*;

public class SistemaDeVendasApplication {

	public static void main(String[] args) {
		// Toda construção de UI Swing deve acontecer na Event Dispatch Thread
		SwingUtilities.invokeLater(() -> {
			// Configuracoes do Swing
			JFrame frame = new JFrame("MainScreen");

			// Instanciação de cada uma das tela que o mainScreen pode abrir
			App app = new App();
			RUDScreen rudScreen = new RUDScreen();

			MainScreen mainScreen = new MainScreen(app, rudScreen);

			frame.setContentPane(mainScreen.getMainScreen());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(1500, 600);
			frame.setVisible(true);
		});

	}

}
