package br.com.assertsistemas.service.impl;

import java.sql.SQLException;
import java.util.List;

import br.com.assertsistemas.dao.ProfessorDAO;
import br.com.assertsistemas.dao.impl.ProfessorDAOImpl;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Professor;
import br.com.assertsistemas.service.ProfessorService;

public class ProfessorServiceImpl implements ProfessorService {

	private ProfessorDAO professorDAO;

	public ProfessorServiceImpl() {
		this.professorDAO = new ProfessorDAOImpl();
	}

	@Override
	public int insert(Professor professor, int usuarioId) throws SQLException {
		return professorDAO.insert(professor, usuarioId);
	}

	@Override
	public boolean update(Professor professor) {
		return professorDAO.update(professor);
	}

	@Override
	public boolean delete(int professorId) {
		return professorDAO.delete(professorId);
	}

	@Override
	public List<Professor> findAll() {
		return professorDAO.findAll();
	}

	@Override
	public Professor findById(int professorId) {
		return professorDAO.findById(professorId);
	}

	@Override
	public List<Professor> findByDisciplina(Disciplina disciplina) {
		return professorDAO.findByDisciplina(disciplina);
	}

}
