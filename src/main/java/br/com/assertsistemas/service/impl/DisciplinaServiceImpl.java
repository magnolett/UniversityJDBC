package br.com.assertsistemas.service.impl;

import java.sql.SQLException;
import java.util.List;

import br.com.assertsistemas.dao.DisciplinaDAO;
import br.com.assertsistemas.dao.impl.DisciplinaDAOImpl;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.service.DisciplinaService;

public class DisciplinaServiceImpl implements DisciplinaService {

	private DisciplinaDAO disciplinaDAO;

	public DisciplinaServiceImpl() {
		this.disciplinaDAO = new DisciplinaDAOImpl();
	}

	@Override
	public Integer insert(Disciplina disciplina) throws SQLException {
		return disciplinaDAO.insert(disciplina);
	}

	@Override
	public boolean update(Disciplina disciplina) {
		return disciplinaDAO.update(disciplina);
	}

	@Override
	public boolean delete(int disciplinaId) {
		return disciplinaDAO.delete(disciplinaId);
	}

	@Override
	public List<Disciplina> findAll() {
		return disciplinaDAO.findAll();
	}

	@Override
	public Disciplina findById(int disciplinaId) {
		return disciplinaDAO.findById(disciplinaId);
	}

	@Override
	public List<Disciplina> findByAluno(String nome) {
		return disciplinaDAO.findByAluno(nome);
	}

}
