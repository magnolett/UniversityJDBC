package br.com.assertsistemas.view.impl;

import javax.swing.JOptionPane;

import br.com.assertsistemas.entity.Tipo;
import br.com.assertsistemas.entity.Usuario;
import br.com.assertsistemas.service.UsuarioService;
import br.com.assertsistemas.service.impl.UsuarioServiceImpl;
import br.com.assertsistemas.view.LoginView;

public class LoginViewController implements LoginView {

	public LoginViewController() {
		UsuarioService usuarioService = new UsuarioServiceImpl();
		String login = JOptionPane.showInputDialog("Insira o seu login:");
		String senha = JOptionPane.showInputDialog("Insira sua senha:");
		Usuario usuario = new Usuario();
		usuarioService.autentica(login, senha);
			if (usuario.getTipoUsuario() == Tipo.ALUNO) {
				
				
			}
			// Envia para tela do aluno;
			if (usuario.getTipoUsuario() == Tipo.COORDENADOR) {
				
				
			}
			// Envia para tela do coordenador;
			if (usuario.getTipoUsuario() == Tipo.PROFESSOR) {
				
				
			}
			// Envia para tela do professor;
				
		
		
	}
}
