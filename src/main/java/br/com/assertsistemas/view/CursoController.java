package br.com.assertsistemas.view;

import java.sql.SQLException;
import java.util.List;

import br.com.assertsistemas.entity.Curso;;

	public interface CursoController {

		public boolean insert(Curso curso) throws SQLException;

		public boolean update(Curso curso);

		public boolean delete(int cursoId);

		public List<Curso> findAll();

		Curso findById(int cursoId);
		
		List <Curso> findByNome(String cursoNome);

	}
