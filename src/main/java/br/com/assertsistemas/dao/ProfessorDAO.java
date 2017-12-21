package br.com.assertsistemas.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Professor;;

public interface ProfessorDAO {

	public boolean update(Professor professor);

	public boolean delete(int professorId);

	public List<Professor> findAll();

	public Professor findById(int professorId);
	
	public List <Professor> findByQualificacao(String qualificacao);
	
	public List <Professor> findByDisciplina(Disciplina disciplina);

	public Professor findByUsuarioId(int id);

	public int insert(Professor professor, int usuarioId) throws SQLException;
	
}
