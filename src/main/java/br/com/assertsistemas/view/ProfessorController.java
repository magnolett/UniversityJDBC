package br.com.assertsistemas.view;

import java.sql.SQLException;
import java.util.List;

import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Professor;;

public interface ProfessorController {

	public boolean update(Professor professor);

	public boolean delete(int professorId);

	public List<Professor> findAll();

	Professor findById(int professorId);
	
	List <Professor> findByDisciplina(Disciplina disciplina);
	
	public Professor cadastroProfessor() throws SQLException;

	boolean insert(Professor professor, int usuarioId) throws SQLException;

}
