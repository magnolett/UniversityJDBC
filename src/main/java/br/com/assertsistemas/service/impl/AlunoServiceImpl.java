package br.com.assertsistemas.service.impl;

import java.util.List;

import br.com.assertsistemas.dao.AlunoDAO;
import br.com.assertsistemas.dao.impl.AlunoDAOImpl;
import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.service.AlunoService;

public class AlunoServiceImpl implements AlunoService {

	private	AlunoDAO alunodao;
	
	public AlunoServiceImpl () {
		this.alunodao = new AlunoDAOImpl();
	}

	
	@Override
	public boolean insert(Aluno aluno, int usuarioId) {
		return alunodao.insert(aluno, usuarioId);
	}

	@Override
	public boolean update(Aluno aluno) {
		return alunodao.update(aluno);
	}

	@Override
	public boolean delete(int alunoId) {
		return alunodao.delete(alunoId);
	}

	@Override
	public List<Aluno> findAll() {
		return alunodao.findAll();
	}

	@Override
	public List<Aluno> findByMatricula(long alunoMatricula) {
		return alunodao.findByMatricula(alunoMatricula);
	}

	@Override
	public Aluno findById(int alunoId) {
		return alunodao.findById(alunoId);
	}
}

