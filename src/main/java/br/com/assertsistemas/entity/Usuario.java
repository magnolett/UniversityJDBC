package br.com.assertsistemas.entity;

public class Usuario  {
	
	private int id;
	private String login;
	private String senha;
	private Tipo tipoUsuario;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Tipo getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(Tipo aluno) {
		this.tipoUsuario = aluno;
	}

	
	
}
