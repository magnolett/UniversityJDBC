package br.com.assertsistemas.service;

import java.util.List;

import br.com.assertsistemas.entity.Desempenho;
import br.com.assertsistemas.entity.Status;

public interface DesempenhoService {

	public boolean insert (Desempenho desempenho);
	
	public boolean update (Desempenho desempenho);
	
	public boolean delete (int desempenhoId);
	
	public List<Desempenho> findAll();

	public Desempenho findById(int desempenhoId);
	
	public List <Desempenho> findByStatus(Status status);
	
	public double updateMediaNotas (Desempenho desempenho);
	
}

