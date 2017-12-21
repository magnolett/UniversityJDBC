package br.com.assertsistemas.service.impl;

import java.sql.SQLException;
import java.util.List;

import br.com.assertsistemas.dao.CursoDAO;
import br.com.assertsistemas.dao.impl.CursoDAOImpl;
import br.com.assertsistemas.entity.Curso;
import br.com.assertsistemas.service.CursoService;

public class CursoServiceImpl implements CursoService {

	private CursoDAO cursoDAO;

	public CursoServiceImpl() {
		this.cursoDAO = new CursoDAOImpl();
	}

	@Override
	public Integer insert(Curso curso) throws SQLException {

		return cursoDAO.insert(curso);
	}

	@Override
	public boolean update(Curso curso) {
		return cursoDAO.update(curso);
	}

	@Override
	public boolean delete(int cursoId) {
		return cursoDAO.delete(cursoId);
	}

	@Override
	public List<Curso> findAll() {
		return cursoDAO.findAll();
	}

	@Override
	public Curso findById(int cursoId) {
		return cursoDAO.findById(cursoId);
	}

	@Override
	public List<Curso> findByNome(String cursoNome) {
		return cursoDAO.findByNome(cursoNome);
	}

}
