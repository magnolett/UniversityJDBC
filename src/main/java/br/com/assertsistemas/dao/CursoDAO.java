package br.com.assertsistemas.dao;

import java.sql.SQLException;
import java.util.List;
import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Disciplina;

import br.com.assertsistemas.entity.Curso;;

	public interface CursoDAO {

		public Integer insert(Curso curso) throws SQLException;

		public boolean update(Curso curso);

		public boolean delete(int cursoId);

		public List<Curso> findAll();

		public Curso findById(int cursoId);
		
		public List <Curso> findByNome(String cursoNome);
		
		public List <Curso> findByCoordenador (Coordenador coordenador);
		
		public List <Curso> findByDisciplina (Disciplina disciplina);

	}
