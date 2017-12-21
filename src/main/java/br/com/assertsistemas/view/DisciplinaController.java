package br.com.assertsistemas.view;

import java.sql.SQLException;
import java.util.List;

import br.com.assertsistemas.entity.Disciplina;;

public interface DisciplinaController {

	public boolean insert(Disciplina disciplina) throws SQLException;

	public boolean update(Disciplina disciplina);

	public boolean delete(int disciplinaId);

	public List<Disciplina> findAll();

	Disciplina findById(int disciplinaId);
	
	List <Disciplina> findByAluno(String nome);

}
