package br.com.assertsistemas.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import br.com.assertsistemas.dao.CursoDAO;
import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Curso;
import br.com.assertsistemas.entity.Disciplina;

public class CursoDAOImpl implements CursoDAO {

	@Override
	public Integer insert(Curso curso) throws SQLException {
		// Abre connexao com o banco
		Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();

		Integer id_coordenador = 0;

		String sql = "insert into coordenador (nome, sexo, idade) values (?,?,?)";

		PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, curso.getCoordenador().getNome());
		statement.setInt(2, curso.getCoordenador().getSexo());
		statement.setInt(3, curso.getCoordenador().getIdade());

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("O Insert foi inserido com sucesso!");
		} else {
			throw new SQLException("Nao foi inserido o Coordenador");
		}

		ResultSet generatedKeys = statement.getGeneratedKeys();
		if (generatedKeys.next()) {
			id_coordenador = generatedKeys.getInt(1);
		} else {
			throw new SQLException("Id coordenador ocoreu um erro");
		}

		// Curso
		int id_curso = 0;

		sql = "insert into curso (nome,id_Coordenador) values (?,?)";
		statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
		statement.setString(1, curso.getNome());
		statement.setInt(2, id_coordenador);
		rowsInserted = statement.executeUpdate();

		if (rowsInserted > 0) {
			System.out.println("O Insert foi inserido com sucesso!");
		} else {
			throw new SQLException("Occoreu um erro ao inserir Curso");
		}

		generatedKeys = statement.getGeneratedKeys();
		if (generatedKeys.next()) {
			id_curso = generatedKeys.getInt(1);
		} else {
			throw new SQLException("Id curso ocoreu um erro");
		}

		int id_disciplina = 0;

		// Disciplina
		for (Disciplina disciplina : curso.getDisciplinas()) {
			sql = "insert into disciplina (codigo, cargaHoraria, nome) values (?,?,?)";
			statement = (PreparedStatement) connectionJDBC.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, disciplina.getCodigo());
			statement.setDouble(2, disciplina.getCargahoraria());
			statement.setString(3, disciplina.getNome());
			rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println("O Insert Disciplina foi inserido com sucesso!");
			} else {
				throw new SQLException("Occoreu um erro ao inserir Disciplina");
			}

			generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id_disciplina = generatedKeys.getInt(1);
			} else {
				throw new SQLException("Id Disciplina ocoreu um erro");
			}

			sql = "insert into curso_disciplina (id_curso, id_disciplina) values (?,?)";
			statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(0, id_curso);
			statement.setInt(1, id_disciplina);
			rowsInserted = statement.executeUpdate();
		}
		// Associativa
		sql = "insert into curso_disciplina (id_curso, id_disciplina) values (?,?)";
		statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
		statement.setInt(0, id_curso);
		statement.setInt(1, id_disciplina);
		rowsInserted = statement.executeUpdate();
		connectionJDBC.close();
		return id_curso;

	}

	@Override
	public boolean update(Curso curso) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "UPDATE curso SET nome=?, WHERE id=?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, curso.getId());
			statement.setString(2, curso.getNome());
			boolean updatesucess = false;
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Os dados já existentes foram atualizados com sucesso!");
			}
			connectionJDBC.close();
			return updatesucess;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean delete(int cursoId) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "delete from curso where id = ?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, cursoId);

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Deleted successfully!");
				return true;
			}

			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Curso> findAll() {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from curso";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			List<Curso> cursos = new ArrayList<>();
			ResultSet resultaQuery = statement.executeQuery();
			while (resultaQuery.next()) {
				Curso curso = new Curso();
				curso.setId(resultaQuery.getInt("id"));
				curso.setNome(resultaQuery.getString("nome"));
				cursos.add(curso);
			}
			return cursos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Curso findById(int cursoId) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from curso where id = ?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, cursoId);

			ResultSet resultaQuery = statement.executeQuery();

			while (resultaQuery.next()) {
				Curso curso = new Curso();
				curso.setNome(resultaQuery.getString("nome"));
				return curso;
			}

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Curso> findByNome(String nomeCurso) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from curso where nome = ?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setString(1, nomeCurso);
			ResultSet resultaQuery = statement.executeQuery();
			List<Curso> cursos = new ArrayList<>();
			while (resultaQuery.next()) {
				Curso curso = new Curso();
				curso.setNome(resultaQuery.getString("nome"));
				cursos.add(curso);
			}
			return cursos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Curso> findByCoordenador(Coordenador coordenador) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "SELECT * FROM projetoaulas.curso c"
					+ "left join projetoaulas.coordenador co on (c.id = co.id_coordenador)";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setString(1, coordenador.getNome());
			ResultSet resultaQuery = statement.executeQuery();
			Curso curso = new Curso();
			List<Curso> cursos = new ArrayList<>();
			while (resultaQuery.next()) {
				curso.setNome(resultaQuery.getString("nome"));
				cursos.add(curso);
			}
			return cursos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Curso> findByDisciplina(Disciplina disciplina) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from projetoaulas.curso c"
					+ "left join projetoaulas.curso_disciplina c_d on (c.id = c_d.id_Curso)"
					+ "left join projetoaulas.disciplina d on (d.id = c_d.id_Disciplina)";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setString(1, disciplina.getNome());
			ResultSet resultaQuery = statement.executeQuery();
			Curso curso = new Curso();
			List<Curso> cursos = new ArrayList<>();
			while (resultaQuery.next()) {
				curso.setNome(resultaQuery.getString("nome"));
				cursos.add(curso);
			}
			return cursos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		CursoDAOImpl cursoDAOImpl = new CursoDAOImpl();

		Curso curso = new Curso();
		curso.setNome("teste");

		List<Disciplina> disciplinas = new ArrayList<>();
		disciplinas.add(new Disciplina(1, "a"));
		disciplinas.add(new Disciplina(2, "b"));
		disciplinas.add(new Disciplina(3, "c"));
		disciplinas.add(new Disciplina(4, "d"));
		disciplinas.add(new Disciplina(5, "e"));
		disciplinas.add(new Disciplina(6, "f"));

		Coordenador coordenador = new Coordenador();
		coordenador.setNome("Rola minha Gigante");

		curso.setCoordenador(coordenador);
		curso.setDisciplinas(disciplinas);

		try {
			cursoDAOImpl.insert(curso);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}