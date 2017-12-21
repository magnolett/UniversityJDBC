package br.com.assertsistemas.view.impl;

import java.sql.SQLException;
import java.util.List;

import br.com.assertsistemas.entity.Curso;
import br.com.assertsistemas.service.CursoService;
import br.com.assertsistemas.service.impl.CursoServiceImpl;
import br.com.assertsistemas.view.CursoController;

public class CursoControllerImpl implements CursoController {

	CursoService cursoService;
	
	public CursoControllerImpl() {
		this.cursoService = new CursoServiceImpl();
	}
	
	
	@Override
	public boolean insert(Curso curso) throws SQLException {
		cursoService.insert(curso);
		return false;
	}

	@Override
	public boolean update(Curso curso) {
		cursoService.update(curso);
		return false;
	}

	@Override
	public boolean delete(int cursoId) {
		cursoService.delete(cursoId);
		return false;
	}

	@Override
	public List<Curso> findAll() {
		cursoService.findAll();
		return null;
	}

	@Override
	public Curso findById(int cursoId) {
		cursoService.findById(cursoId);
		return null;
	}

	@Override
	public List<Curso> findByNome(String cursoNome) {
		cursoService.findByNome(cursoNome);
		return null;
	}
}
