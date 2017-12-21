package br.com.assertsistemas.entity;

import java.util.List;

public class Aluno extends Pessoa {

	private long matricula;
	private int semestre;
	private List<Disciplina> disciplinas;

	
	public Aluno(long matricula, String nome) {
		this.matricula = matricula;
		this.nome = nome;
	}

	public long getMatricula() {
		return matricula;
	}

	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	@Override
	public String toString() {
		return "Aluno" + super.toString() + "[ matricula=" + matricula + ", semestre=" + semestre + ", disciplinas="
				+ disciplinas + "]";
	}

}
