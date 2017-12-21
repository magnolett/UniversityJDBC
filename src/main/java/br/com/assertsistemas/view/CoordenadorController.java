package br.com.assertsistemas.view;

import java.sql.SQLException;
import java.util.List;

import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Desempenho;
import br.com.assertsistemas.entity.Professor;

public interface CoordenadorController {

	public boolean insert (Coordenador coordenador) throws SQLException;
	
	public boolean update (Coordenador coordenador);
	
	public boolean delete (int coordenadorId);
	
	public List<Coordenador> findAll();

	Coordenador findById(int coordenadorId);
	
	public static Aluno cadastroAluno(String info) {
		return null;
	}
	
	public static Professor cadastroProfessor() {
		return null;
	}
	
	Coordenador findByDesempenho (Desempenho desempenho);
}