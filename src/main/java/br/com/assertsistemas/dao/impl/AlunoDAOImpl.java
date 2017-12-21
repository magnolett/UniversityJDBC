package br.com.assertsistemas.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.assertsistemas.dao.AlunoDAO;
import br.com.assertsistemas.dao.DisciplinaDAO;
import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Usuario;

public class AlunoDAOImpl implements AlunoDAO {

	@Override
	public boolean insert(Aluno aluno, int id) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "insert into aluno (nome, sexo, idade, matricula, semestre, id_Usuario) values (?,?,?,?,?,?)";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setString(1, aluno.getNome());
			statement.setString(2, String.valueOf(aluno.getSexo()));
			statement.setInt(3, aluno.getIdade());
			statement.setLong(4, aluno.getMatricula());
			statement.setInt(5, aluno.getSemestre());
			statement.setInt(6, aluno.getUsuario().getId());
			boolean insertSucess = false;
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("O Insert foi inserido com sucesso!");
				insertSucess = true;
			}
			connectionJDBC.close();
			return insertSucess;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Aluno aluno) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "UPDATE aluno SET nome=?, sexo=?, matricula=?, semestre=?, WHERE id=?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, aluno.getId());
			statement.setString(2, aluno.getNome());
			statement.setString(2, String.valueOf(aluno.getSexo()));
			statement.setInt(4, aluno.getIdade());
			statement.setLong(5, aluno.getMatricula());
			statement.setInt(6, aluno.getSemestre());
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
	public boolean delete(int alunoId) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "delete from aluno where id = ?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, alunoId);

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
	public List<Aluno> findAll() {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from aluno";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			List<Aluno> alunos = new ArrayList<>();
			ResultSet resultaQuery = statement.executeQuery();
			while (resultaQuery.next()) {
				Aluno aluno = new Aluno(0, sql);
				aluno.setId(resultaQuery.getInt("id"));
				aluno.setNome(resultaQuery.getString("nome"));
				aluno.setSexo(resultaQuery.getCharacterStream("sexo").toString().charAt(0));
				aluno.setIdade(resultaQuery.getInt("idade"));
				aluno.setMatricula(resultaQuery.getLong("matricula"));
				aluno.setSemestre(resultaQuery.getInt("semestre"));
				DisciplinaDAO disciplinaDAO = new DisciplinaDAOImpl();
				List<Disciplina> disciplinas = disciplinaDAO.findAll();
				aluno.setDisciplinas(disciplinas);
				alunos.add(aluno);
			}
			return alunos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Aluno findById(int alunoId) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from aluno where id = ?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, alunoId);

			ResultSet resultaQuery = statement.executeQuery();

			while (resultaQuery.next()) {
				Aluno aluno = new Aluno(alunoId, sql);
				aluno.setId(resultaQuery.getInt("id"));
				aluno.setNome(resultaQuery.getString("nome"));
				aluno.setSexo(resultaQuery.getCharacterStream("sexo").toString().charAt(0));
				aluno.setIdade(resultaQuery.getInt("idade"));
				aluno.setMatricula(resultaQuery.getLong("matricula"));
				aluno.setSemestre(resultaQuery.getInt("semestre"));
				return aluno;
			}

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Aluno> findByMatricula(long alunoMatricula) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from aluno where matricula = ?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setLong(1, alunoMatricula);
			ResultSet resultaQuery = statement.executeQuery();
			List<Aluno> alunos = new ArrayList<>();
			while (resultaQuery.next()) {
				Aluno aluno = new Aluno(alunoMatricula, sql);
				aluno.setId(resultaQuery.getInt("id"));
				aluno.setNome(resultaQuery.getString("nome"));
				aluno.setSexo(resultaQuery.getCharacterStream("sexo").toString().charAt(0));
				aluno.setIdade(resultaQuery.getInt("idade"));
				aluno.setMatricula(resultaQuery.getLong("matricula"));
				aluno.setSemestre(resultaQuery.getInt("semestre"));
				alunos.add(aluno);
			}
			return alunos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Aluno> findByLikeCurso(String alunoCurso) {
		try {
			System.out.println(alunoCurso);
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "SELECT * FROM aluno WHERE curso LIKE ?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setString(1, "%" + alunoCurso + "%");
			ResultSet resultaQuery = statement.executeQuery();
			List<Aluno> alunos = new ArrayList<>();
			while (resultaQuery.next()) {
				Aluno aluno = new Aluno(0, sql);
				aluno.setId(resultaQuery.getInt("id"));
				aluno.setNome(resultaQuery.getString("nome"));
				aluno.setSexo(resultaQuery.getCharacterStream("sexo").toString().charAt(0));
				aluno.setIdade(resultaQuery.getInt("idade"));
				aluno.setMatricula(resultaQuery.getLong("matricula"));
				aluno.setSemestre(resultaQuery.getInt("semestre"));
				alunos.add(aluno);
			}
			return alunos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Aluno> findByDisciplina(Disciplina disciplina) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from projetoaulas.aluno a"
					+ "left join projetoaulas.aluno_disciplina a_d on (a.id = a_d.id_Aluno)"
					+ "left join projetoaulas.disciplina d on (d.id = a_d.id_disciplina)";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setString(1, disciplina.getNome());
			ResultSet resultaQuery = statement.executeQuery();
			List<Aluno> alunos = new ArrayList<>();
			while (resultaQuery.next()) {
				Aluno aluno = new Aluno(0, sql);
				aluno.setId(resultaQuery.getInt("id"));
				aluno.setNome(resultaQuery.getString("nome"));
				aluno.setSexo(resultaQuery.getCharacterStream("sexo").toString().charAt(0));
				aluno.setIdade(resultaQuery.getInt("idade"));
				aluno.setMatricula(resultaQuery.getLong("matricula"));
				aluno.setSemestre(resultaQuery.getInt("semestre"));
				alunos.add(aluno);
			}
			return alunos;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Aluno findByUsuarioId(int id) {

		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			Usuario usuario = new Usuario();
			String sql = "select * from projetoaulas.aluno a"
					+ "left join projetoaulas.usuario u on (u.id = a.id_Usuario)";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, usuario.getId());
			ResultSet resultaQuery = statement.executeQuery();
			while (resultaQuery.next()) {
				Aluno aluno = new Aluno(id, sql);
				aluno.setId(resultaQuery.getInt("id"));
				aluno.setNome(resultaQuery.getString("nome"));
				aluno.setSexo(resultaQuery.getCharacterStream("sexo").toString().charAt(0));
				aluno.setIdade(resultaQuery.getInt("idade"));
				aluno.setMatricula(resultaQuery.getLong("matricula"));
				aluno.setSemestre(resultaQuery.getInt("semestre"));
				return aluno;

			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}