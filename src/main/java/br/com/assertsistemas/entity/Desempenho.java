package br.com.assertsistemas.entity;

import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Status;

public class Desempenho {

	private int id;
	private double medianota;
	private Aluno aluno;
	private Disciplina disciplina;
	private double nota1;
	private double nota2;
	private double nota3;
	private Status status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMedianota() {
		return medianota;
	}

	public void setMedianota(double medianota) {
		this.medianota = medianota;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public double getNota1() {
		return nota1;
	}

	public void setNota1(double nota1) {
		this.nota1 = nota1;
	}

	public double getNota2() {
		return nota2;
	}

	public void setNota2(double nota2) {
		this.nota2 = nota2;
	}

	public double getNota3() {
		return nota3;
	}

	public void setNota3(double nota3) {
		this.nota3 = nota3;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
