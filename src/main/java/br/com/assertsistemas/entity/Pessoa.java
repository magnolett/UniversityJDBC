package br.com.assertsistemas.entity;

public abstract class Pessoa {

	protected int id;
	protected String nome;
	protected char sexo;
	protected int idade;
	protected Usuario usuario;
	
	
	
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
		
		
	}

	
	
	
	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", sexo=" + sexo + ", idade=" + idade + "]";
	}
}
