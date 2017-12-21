package br.com.assertsistemas.dao;

import java.util.List;

import br.com.assertsistemas.entity.Desempenho;
import br.com.assertsistemas.entity.Status;

public interface DesempenhoDAO {

	public boolean insert(Desempenho desempenho);

	public boolean update(Desempenho desempenho);

	public boolean delete(int desempenhoId);

	public List<Desempenho> findAll();

	public Desempenho findById(int desempenhoId);
	
	public List <Desempenho> findByStatus(Status status);

	public Desempenho findByAlunoId(int id);

	public Desempenho findByDisciplinaId(int id);
	
}
