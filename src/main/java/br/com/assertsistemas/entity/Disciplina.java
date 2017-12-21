package br.com.assertsistemas.entity;

import java.util.List;

public class Disciplina {

	private int id;
	private long codigo;
	private double cargahoraria;
	private String nome;
	private Curso curso;
	private List <Aluno> alunos;
	private Professor professor;
	
	
	
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Disciplina() {}
	
	public Disciplina(long codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public double getCargahoraria() {
		return cargahoraria;
	}

	public void setCargahoraria(double cargahoraria) {
		this.cargahoraria = cargahoraria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Curso getCurso() { 
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
	return "Disciplina [id=" + id + ", codigo=" + codigo + ", cargahoraria=" + cargahoraria + ", nome=" + nome
				+ ", curso=" + curso + "]";
	}

}
	

