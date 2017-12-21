package br.com.assertsistemas.service;

import java.sql.SQLException;
import java.util.List;

import br.com.assertsistemas.entity.Disciplina;

public interface DisciplinaService {

	public Integer insert(Disciplina disciplina) throws SQLException;

	public boolean update(Disciplina disciplina);

	public boolean delete(int disciplinaId);

	public List<Disciplina> findAll();

	public Disciplina findById(int disciplinaId);
	
	public List <Disciplina> findByAluno(String nome);

	
	
}
