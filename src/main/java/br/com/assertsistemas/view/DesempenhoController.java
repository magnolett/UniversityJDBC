package br.com.assertsistemas.view;

import java.util.List;

import br.com.assertsistemas.entity.Desempenho;
import br.com.assertsistemas.entity.Status;

public interface DesempenhoController {

	public boolean insert(Desempenho desempenho);

	public boolean update(Desempenho desempenho);

	public boolean delete(int desempenhoId);

	public List<Desempenho> findAll();

	public Desempenho findById(int desempenhoId);
	
	public List <Desempenho> findByStatus(Status status);
	
	public double cadastroNotas (double nota1, double nota2, double nota3, double notaMedia);
	
}
