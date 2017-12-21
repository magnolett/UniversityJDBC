package br.com.assertsistemas.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import br.com.assertsistemas.dao.DisciplinaDAO;
import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Curso;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Professor;

public class DisciplinaDAOImpl implements DisciplinaDAO {

	@Override
	public Integer insert(Disciplina disciplina) throws SQLException {
		Integer id_professor = 0;

		// Banco
		Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
		// PROFESSOR
		String sql = "insert into professor (nome, sexo, idade, qualificacao, id_Usuario) values (?,?,?,?,?)";
		PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, disciplina.getProfessor().getNome());
		statement.setInt(2, disciplina.getProfessor().getSexo());
		statement.setInt(3, disciplina.getProfessor().getIdade());
		statement.setString(4, disciplina.getProfessor().getQualificacao());
		statement.setInt(5, disciplina.getProfessor().getId_usuario());
		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("O Insert foi inserido com sucesso!");
		}

		ResultSet generatedKeys = statement.getGeneratedKeys();
		if (generatedKeys.next()) {
			id_professor = generatedKeys.getInt(1);
		} else {
			throw new SQLException("erro no ssytem");
		}

		// Disciplinas
		sql = "insert into disciplina (codigo, id_professor, cargaHoraria, nome) values (?,?,?,?)";
		statement = (PreparedStatement) connectionJDBC.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setLong(1, disciplina.getCodigo());
		statement.setInt(2, id_professor);
		statement.setDouble(3, disciplina.getCargahoraria());
		statement.setString(4, disciplina.getNome());
		generatedKeys = statement.getGeneratedKeys();
		Integer id_disciplina = generatedKeys.getInt(1);
		rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("O Insert foi inserido com sucesso!");
		}

		int id_aluno = 0;
		// Aluno
		for (Aluno aluno : disciplina.getAlunos()) {
			sql = "insert into aluno (nome, sexo, idade, matricula, semestre, id_Usuario) values (?,?,?,?,?,?)";
			statement = (PreparedStatement) connectionJDBC.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, aluno.getNome());
			statement.setString(2, String.valueOf(aluno.getSexo()));
			statement.setInt(3, aluno.getIdade());
			statement.setLong(4, aluno.getMatricula());
			statement.setInt(5, aluno.getSemestre());
			rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println("O insert do aluno foi inserido com sucesso!");
			} else {
				throw new SQLException("O insert do aluno não pode ser inserido!");
			}
			generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id_aluno = generatedKeys.getInt(1);
			} else {
				throw new SQLException();
			}
		}
		// Associativa
		sql = "insert into aluno_disciplina (id_aluno, id_disciplina) values (?,?)";
		statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
		statement.setInt(0, id_aluno);
		statement.setInt(1, id_disciplina);
		rowsInserted = statement.executeUpdate();
		connectionJDBC.close();
		return id_disciplina;
	}

	@Override
	public boolean update(Disciplina disciplina) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "UPDATE disciplina SET codigo=?, cargaHoraria=?, nome=?, WHERE id=?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, disciplina.getId());
			statement.setLong(2, disciplina.getCodigo());
			statement.setDouble(3, disciplina.getCargahoraria());
			statement.setString(4, disciplina.getNome());
			boolean updatesucess = false;
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Os dados já existentes foram atualizados com sucesso!");
			}
			updatesucess = true;
			connectionJDBC.close();
			return updatesucess;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean delete(int disciplinaId) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "delete from disciplina where id = ?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, disciplinaId);
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
	public List<Disciplina> findAll() {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from disciplina";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			List<Disciplina> disciplinas = new ArrayList<>();
			ResultSet resultaQuery = statement.executeQuery();
			while (resultaQuery.next()) {
				Disciplina disciplina = new Disciplina();
				disciplina.setId(resultaQuery.getInt("id"));
				disciplina.setCodigo(resultaQuery.getLong("codigo"));
				disciplina.setCargahoraria(resultaQuery.getDouble("cargahoraria"));
				disciplina.setNome(resultaQuery.getString("nome"));
				disciplinas.add(disciplina);
			}
			return disciplinas;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Disciplina findById(int disciplinaId) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from disciplina where id = ?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, disciplinaId);
			ResultSet resultaQuery = statement.executeQuery();

			while (resultaQuery.next()) {
				Disciplina disciplina = new Disciplina();
				disciplina.setId(resultaQuery.getInt("id"));
				disciplina.setCodigo(resultaQuery.getLong("codigo"));
				disciplina.setCargahoraria(resultaQuery.getDouble("cargahoraria"));
				disciplina.setNome(resultaQuery.getString("nome"));
				return disciplina;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Disciplina> findByCodigo(long disciplinaCodigo) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from disciplina where codigo = ?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setLong(1, disciplinaCodigo);

			ResultSet resultaQuery = statement.executeQuery();
			List<Disciplina> disciplinas = new ArrayList<>();
			while (resultaQuery.next()) {
				Disciplina disciplina = new Disciplina();
				disciplina.setId(resultaQuery.getInt("id"));
				disciplina.setCodigo(resultaQuery.getLong("codigo"));
				disciplina.setCargahoraria(resultaQuery.getDouble("cargahoraria"));
				disciplina.setNome(resultaQuery.getString("nome"));
				disciplinas.add(disciplina);
			}
			return disciplinas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Disciplina> findByCurso(Curso curso) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "SELECT * FROM PROJETOAULAS.DISCIPLINA D"
					+ "LEFT JOIN PROJETOAULAS.CURSO_DISCIPLINA C_D ON (D.ID = C_D.ID_DISCIPLINA)"
					+ "LEFT JOIN PROJETOAULAS.CURSO C ON (C.ID = C_D.ID_CURSO)";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setString(1, curso.getNome());
			ResultSet resultaQuery = statement.executeQuery();
			List<Disciplina> disciplinas = new ArrayList<>();
			while (resultaQuery.next()) {
				Disciplina disciplina = new Disciplina();
				disciplina.setId(resultaQuery.getInt("id"));
				disciplina.setCodigo(resultaQuery.getLong("codigo"));
				disciplina.setCargahoraria(resultaQuery.getDouble("cargahoraria"));
				disciplina.setNome(resultaQuery.getString("nome"));
				disciplinas.add(disciplina);
			}
			return disciplinas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Disciplina> findByAluno(String nomeDisciplina) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();

			String sql = "select * from projetoaulas.disciplina d "
					+ "left join projetoaulas.aluno_disciplina a_d on (d.id = a_d.id_Disciplina)"
					+ "left join projetoaulas.aluno a on (a.id = a_d.id_Aluno)" + "where d.nome = ?";

			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setString(1, nomeDisciplina);

			ResultSet resultaQuery = statement.executeQuery();
			List<Disciplina> disciplinas = new ArrayList<>();

			while (resultaQuery.next()) {
				Disciplina disciplina = new Disciplina();

				disciplina.setId(resultaQuery.getInt("id"));
				disciplina.setCodigo(resultaQuery.getLong("codigo"));
				disciplina.setCargahoraria(resultaQuery.getDouble("cargahoraria"));
				disciplina.setNome(resultaQuery.getString("nome"));

				disciplinas.add(disciplina);
			}

			return disciplinas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		DisciplinaDAOImpl dao = new DisciplinaDAOImpl();

		Disciplina disciplina = new Disciplina();
		disciplina.setNome("Tetando");
		List<Aluno> alunos = new ArrayList<>();
		alunos.add(new Aluno(1, "Trevoso"));
		alunos.add(new Aluno(2, "Hello Darkness"));
		alunos.add(new Aluno(3, "My Old Friend"));
		alunos.add(new Aluno(4, "d"));
		alunos.add(new Aluno(5, "e"));
		alunos.add(new Aluno(6, "f"));

		Professor professor = new Professor();
		professor.setNome("tiotio");
		professor.getId();
		disciplina.setAlunos(alunos);
		disciplina.setProfessor(professor);
		try {
			dao.insert(disciplina);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}