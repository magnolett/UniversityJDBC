package br.com.assertsistemas.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Desempenho;;

public interface CoordenadorDAO {

	public Integer insert(Coordenador coordenador, int usuarioId) throws SQLException;

	public boolean update(Coordenador coordenador);

	public boolean delete(int coordenadorId);

	public List<Coordenador> findAll();

	public Coordenador findById(int coordenadorId);
	
	public Coordenador findByDesempenho (Desempenho desempenho);

	public Coordenador findByUsuarioId(int id);
	
	
	
	
	
}