package br.com.assertsistemas.service.impl;

import java.util.List;

import br.com.assertsistemas.dao.DesempenhoDAO;
import br.com.assertsistemas.dao.impl.DesempenhoDAOImpl;
import br.com.assertsistemas.entity.Desempenho;
import br.com.assertsistemas.entity.Status;
import br.com.assertsistemas.service.DesempenhoService;

public class DesempenhoServiceImpl implements DesempenhoService {
	public DesempenhoDAO desempenhoDAO;

	public DesempenhoServiceImpl() {
		this.desempenhoDAO = new DesempenhoDAOImpl();

	}

	// DesempenhoService desempenhoService = new DesempenhoServiceImpl();
	
	
	@Override
	public double updateMediaNotas(Desempenho desempenho) {
		double n1 = desempenho.getNota1();
		double n2 = desempenho.getNota2();
		double n3 = desempenho.getNota3();
		double mediaNota = n1 + n2 + n3 / 3;
		desempenho.setMedianota(mediaNota);
		desempenho.setStatus(resultadoDesempenho(desempenho));
		desempenhoDAO.update(desempenho);
		return mediaNota;
	}

	private Status resultadoDesempenho(Desempenho desempenho) {

		if ((desempenho.getNota1() == 0) || (desempenho.getNota2() == 0) || (desempenho.getNota3() == 0)) {
			return Status.EM_ANDAMENTO;
		}
		if (desempenho.getMedianota() > 6) {
			return Status.APROVADO;

		} else if ((desempenho.getMedianota() > 5) && (desempenho.getMedianota() < 6)) {
			return Status.PENDENTE;

		} else if (desempenho.getMedianota() < 5) {
			return Status.REPROVADO;
		}

		return null;
	}

	@Override
	public boolean insert(Desempenho desempenho) {
		return desempenhoDAO.insert(desempenho);

	}

	@Override
	public boolean update(Desempenho desempenho) {
		return desempenhoDAO.update(desempenho);
	}

	@Override
	public boolean delete(int desempenhoId) {
		return desempenhoDAO.delete(desempenhoId);
	}

	@Override
	public List<Desempenho> findAll() {
		return desempenhoDAO.findAll();
	}

	@Override
	public Desempenho findById(int desempenhoId) {
		return desempenhoDAO.findById(desempenhoId);
	}

	@Override
	public List<Desempenho> findByStatus(Status status) {
		return desempenhoDAO.findByStatus(status);
	}

}
