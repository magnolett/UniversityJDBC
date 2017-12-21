package br.com.assertsistemas.view.impl;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.assertsistemas.entity.Desempenho;
import br.com.assertsistemas.entity.Status;
import br.com.assertsistemas.service.DesempenhoService;
import br.com.assertsistemas.service.impl.DesempenhoServiceImpl;
import br.com.assertsistemas.util.Validacao;
import br.com.assertsistemas.view.DesempenhoController;

public class DesempenhoControllerImpl implements DesempenhoController {

	DesempenhoService desempenhoService;
	
	public DesempenhoControllerImpl() {
		this.desempenhoService = new DesempenhoServiceImpl();
	}
	
	
	@Override
	public boolean insert(Desempenho desempenho) {
		desempenhoService.insert(desempenho);
		return false;
	}

	@Override
	public boolean update(Desempenho desempenho) {
		desempenhoService.update(desempenho);
		return false;
	}

	@Override
	public boolean delete(int desempenhoId) {
		desempenhoService.delete(desempenhoId);
		return false;
	}

	@Override
	public List<Desempenho> findAll() {
		desempenhoService.findAll();
		return null;
	}

	@Override
	public Desempenho findById(int desempenhoId) {
		desempenhoService.findById(desempenhoId);
		return null;
	}

	@Override
	public List<Desempenho> findByStatus(Status status) {
		desempenhoService.findByStatus(status);
		return null;
	}


	@Override
	public double cadastroNotas(double nota1, double nota2, double nota3, double mediaNota) {

		Desempenho desempenho = new Desempenho();
		String n1 = JOptionPane.showInputDialog("Insira a primeira nota!");
		nota1 = Double.valueOf(n1);
		Validacao valid = new Validacao();
		if (valid.validDouble(n1)) {
			desempenho.setNota1(nota1);
		} else {
			String repeat = JOptionPane.showInputDialog("Somente números!");
			double repeat1 = Double.valueOf(repeat);
			desempenho.setNota1(repeat1);
		}
		String n2 = JOptionPane.showInputDialog("Insira a segunda nota!");
		nota2 = Double.valueOf(n2);
		if (valid.validDouble(n2)) {
			desempenho.setNota2(nota2);
		} else {
			String repeat = JOptionPane.showInputDialog("Somente números!");
			double repeat1 = Double.valueOf(repeat);
			desempenho.setNota2(repeat1);
		}
		String n3 = JOptionPane.showInputDialog("Insira a terceira nota!");
		nota3 = Double.valueOf(n3);
		if (valid.validDouble(n3)) {
			desempenho.setNota3(nota3);
		} else {
			String repeat = JOptionPane.showInputDialog("Somente números!");
			double repeat1 = Double.valueOf(repeat);
			desempenho.setNota3(repeat1);
		}
		DesempenhoService desempenhoService = new DesempenhoServiceImpl();
		mediaNota = desempenhoService.updateMediaNotas(desempenho);
		
		
		return mediaNota;
	}

}
