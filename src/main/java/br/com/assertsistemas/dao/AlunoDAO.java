package br.com.assertsistemas.dao;

import java.util.List;

import br.com.assertsistemas.entity.Aluno;;

public interface AlunoDAO {


	public boolean update(Aluno aluno);

	public boolean delete(int alunoId);

	public List<Aluno> findAll();

	public Aluno findById(int alunoId);
	
	public List <Aluno> findByMatricula(long alunoMatricula);
	
	public List <Aluno> findByLikeCurso(String alunoCurso);

	public Aluno findByUsuarioId(int id);

	boolean insert(Aluno aluno, int usuarioId);	
	
}
