package br.com.assertsistemas.service;

import java.sql.SQLException;
import java.util.List;

import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Professor;

public interface ProfessorService {
	
	public int insert (Professor professor, int usuarioId) throws SQLException;
	
	public boolean update (Professor professor);
	
	public boolean delete (int professorId);
	
	public List<Professor> findAll();

	public Professor findById(int professorId);

	public List <Professor> findByDisciplina(Disciplina disciplina);
	
	

}
