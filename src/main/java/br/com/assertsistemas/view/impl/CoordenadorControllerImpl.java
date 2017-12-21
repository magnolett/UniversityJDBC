package br.com.assertsistemas.view.impl;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Desempenho;
import br.com.assertsistemas.service.CoordenadorService;
import br.com.assertsistemas.service.impl.CoordenadorServiceImpl;
import br.com.assertsistemas.view.AlunoController;
import br.com.assertsistemas.view.CoordenadorController;
import br.com.assertsistemas.view.ProfessorController;

public class CoordenadorControllerImpl implements CoordenadorController {

	private CoordenadorService service;
		
	public CoordenadorControllerImpl() {
		this.service = new CoordenadorServiceImpl();
		
	}
	
	public static void main(String[] args) throws SQLException {
		AlunoController aController = new AlunoControllerImpl();
		ProfessorController pController = new ProfessorControllerImpl();
		String option = JOptionPane.showInputDialog("Digite 1 para cadastrar aluno ou 2 para cadastrar professor");
		int a = Integer.valueOf(option);
		if (a == 1) {
			aController.cadastroAluno();
		} else if (a == 2) {
			pController.cadastroProfessor();
		}
	}
		
	@Override
	public boolean insert(Coordenador coordenador) throws SQLException {
		service.insert(coordenador, 0);
		return false;
	}

	@Override
	public boolean update(Coordenador coordenador) {
		service.update(coordenador);
		return false;
	}

	@Override
	public boolean delete(int coordenadorId) {
		service.delete(coordenadorId);
		return false;
		
	}

	@Override
	public List<Coordenador> findAll() {
		service.findAll();
		return null;
	}

	@Override
	public Coordenador findById(int coordenadorId) {
		service.findById(coordenadorId);
		return null;
	}



	@Override
	public Coordenador findByDesempenho(Desempenho desempenho) {
		service.findByDesempenho(desempenho);
		return null;
	}
}