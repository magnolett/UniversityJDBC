package br.com.assertsistemas.dao;

import br.com.assertsistemas.entity.Usuario;

public interface UsuarioDAO {

	public Usuario findByLoginAndSenha(String login, String senha);
	
	public Integer insert (Usuario usuario);
	
	public int findById(int id);

	

	
	
}
