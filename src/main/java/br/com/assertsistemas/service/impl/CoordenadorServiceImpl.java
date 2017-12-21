package br.com.assertsistemas.service.impl;

import java.sql.SQLException;
import java.util.List;

import br.com.assertsistemas.dao.CoordenadorDAO;
import br.com.assertsistemas.dao.impl.CoordenadorDAOImpl;
import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Desempenho;
import br.com.assertsistemas.service.CoordenadorService;

public class CoordenadorServiceImpl implements CoordenadorService {

	private CoordenadorDAO coordenadorDAO;
	
	public CoordenadorServiceImpl() {
		this.coordenadorDAO = new CoordenadorDAOImpl();
	}
	
	@Override
	public Integer insert(Coordenador coordenador, int usuarioId) throws SQLException {
		return coordenadorDAO.insert(coordenador, usuarioId);

	}

	@Override
	public boolean update(Coordenador coordenador) {
		return coordenadorDAO.update(coordenador);
	}

	@Override
	public boolean delete(int coordenadorId) {
		return coordenadorDAO.delete(coordenadorId);
	}

	@Override
	public List<Coordenador> findAll() {
		return coordenadorDAO.findAll();
	}

	@Override
	public Coordenador findById(int coordenadorId) {
		return coordenadorDAO.findById(coordenadorId);
	}

	@Override
	public Coordenador findByDesempenho(Desempenho desempenho) {
		return coordenadorDAO.findByDesempenho(desempenho);
	}

		
}
		

