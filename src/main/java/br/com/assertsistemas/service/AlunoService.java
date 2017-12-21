package br.com.assertsistemas.service;

import java.util.List;

import br.com.assertsistemas.entity.Aluno;

public interface AlunoService {


	public boolean update (Aluno aluno);
	
	public boolean delete (int alunoId);
	
	public List<Aluno> findAll();
	
	public List <Aluno> findByMatricula(long alunoMatricula);

	public Aluno findById(int alunoId);

	boolean insert(Aluno aluno, int usuarioId);

	
	
	
	
}
