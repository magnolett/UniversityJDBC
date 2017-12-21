package br.com.assertsistemas.service;

import java.sql.SQLException;
import java.util.List;

import br.com.assertsistemas.entity.Curso;

public interface CursoService {

	public Integer insert(Curso curso) throws SQLException;

	public boolean update(Curso curso);

	public boolean delete(int cursoId);

	public List<Curso> findAll();

	public Curso findById(int cursoId);
	
	public List <Curso> findByNome(String cursoNome);
	
}
