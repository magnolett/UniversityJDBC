package br.com.assertsistemas.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.assertsistemas.entity.Curso;
import br.com.assertsistemas.entity.Disciplina;;

public interface DisciplinaDAO {

	public Integer insert(Disciplina disciplina) throws SQLException;

	public boolean update(Disciplina disciplina);

	public boolean delete(int disciplinaId);

	public List<Disciplina> findAll();

	public Disciplina findById(int disciplinaId);
	
	public List <Disciplina> findByCodigo(long disciplinaCodigo);
	
	public	List <Disciplina> findByCurso (Curso curso);
	
	public	List <Disciplina> findByAluno(String nome);


}
