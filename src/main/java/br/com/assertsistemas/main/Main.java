package br.com.assertsistemas.main;

import javax.swing.JOptionPane;

public class Main {

	public static void main (String[]args) {
		
		String usuario = JOptionPane.showInputDialog("Insira o seu login:");
		String senha = JOptionPane.showInputDialog("Insira sua senha:");
		
		JOptionPane.showConfirmDialog(null, "Confirma Login?" );
		
	}
	
	
}
