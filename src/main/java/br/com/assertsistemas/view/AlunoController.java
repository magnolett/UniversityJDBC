package br.com.assertsistemas.view;

import java.util.List;

import br.com.assertsistemas.entity.Aluno;;

public interface AlunoController {

	public boolean insert(Aluno aluno);

	public boolean update(Aluno aluno);

	public boolean delete(int alunoId);

	public List<Aluno> findAll();

	Aluno findById(int alunoId);
	
	List <Aluno> findByMatricula(long alunoMatricula);
	
	Aluno cadastroAluno();
	
}
