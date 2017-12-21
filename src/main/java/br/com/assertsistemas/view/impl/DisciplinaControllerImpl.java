package br.com.assertsistemas.view.impl;

import java.sql.SQLException;
import java.util.List;

import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.service.DisciplinaService;
import br.com.assertsistemas.service.impl.DisciplinaServiceImpl;
import br.com.assertsistemas.view.DisciplinaController;

public class DisciplinaControllerImpl implements DisciplinaController {

	DisciplinaService disciplinaService;
	
	public DisciplinaControllerImpl() {
		this.disciplinaService = new DisciplinaServiceImpl();
	}
	
	
	@Override
	public boolean insert(Disciplina disciplina) throws SQLException {
		disciplinaService.insert(disciplina);
		return false;
	}

	@Override
	public boolean update(Disciplina disciplina) {
		disciplinaService.update(disciplina);
		return false;
	}

	@Override
	public boolean delete(int disciplinaId) {
		disciplinaService.delete(disciplinaId);
		return false;
	}

	@Override
	public List<Disciplina> findAll() {
		disciplinaService.findAll();
		return null;
	}

	@Override
	public Disciplina findById(int disciplinaId) {
		disciplinaService.findById(disciplinaId);
		return null;
	}

	@Override
	public List<Disciplina> findByAluno(String nome) {
		disciplinaService.findByAluno(nome);
		return null;
	}

}
