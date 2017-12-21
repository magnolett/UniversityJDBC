package br.com.assertsistemas.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.assertsistemas.dao.ProfessorDAO;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Professor;
import br.com.assertsistemas.entity.Usuario;

public class ProfessorDAOImpl implements ProfessorDAO {

	@Override
	public int insert(Professor professor, int usuarioId) throws SQLException {
		Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
		String sql = "insert into professor (nome, sexo, idade, qualificacao, id_Usuario) values (?,?,?,?,?)";
		PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, professor.getNome());
		statement.setString(2, String.valueOf(professor.getSexo()));
		statement.setInt(3, professor.getIdade());
		statement.setString(4, professor.getQualificacao());
		statement.setInt(5, usuarioId);
		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("O Insert foi inserido com sucesso!");
		}
		ResultSet generatedKeys = statement.getGeneratedKeys();
		if (generatedKeys.next()) {
			return generatedKeys.getInt(1);
		} else {
			throw new SQLException("erro no ssytem");
		}
	}

	@Override
	public boolean update(Professor professor) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "update professor SET nome=?, sexo=?, idade=?, qualificacao=? WHERE id=?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, professor.getId());
			statement.setString(2, professor.getNome());
			statement.setInt(3, professor.getSexo());
			statement.setInt(4, professor.getIdade());
			statement.setString(5, professor.getQualificacao());
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int professorId) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "delete from professor where id = ?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, professorId);
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
	public List<Professor> findAll() {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from professor";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			List<Professor> professores = new ArrayList<>();
			ResultSet resultaQuery = statement.executeQuery();
			while (resultaQuery.next()) {

				Professor professor = new Professor();
				professor.setId(resultaQuery.getInt("id"));
				professor.setNome(resultaQuery.getString("nome"));
				professor.setSexo(resultaQuery.getBytes("sexo").toString().charAt(0));
				professor.setIdade(resultaQuery.getInt("idade"));
				professor.setQualificacao(resultaQuery.getString("qualificacao"));
				professores.add(professor);
			}
			return professores;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Professor findById(int professorId) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from  professor where id = ?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, professorId);

			ResultSet resultaQuery = statement.executeQuery();

			while (resultaQuery.next()) {
				Professor professor = new Professor();
				professor.setId(resultaQuery.getInt("id"));
				professor.setNome(resultaQuery.getString("nome"));
				professor.setSexo(resultaQuery.getBytes("sexo").toString().charAt(0));
				professor.setIdade(resultaQuery.getInt("idade"));
				professor.setQualificacao(resultaQuery.getString("qualificacao"));
				return professor;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Professor> findByQualificacao(String qualificacao) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from professor where qualificacao = ?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setString(1, qualificacao);
			ResultSet resultaQuery = statement.executeQuery();
			List<Professor> professores = new ArrayList<>();
			while (resultaQuery.next()) {
				Professor professor = new Professor();
				professor.setId(resultaQuery.getInt("id"));
				professor.setNome(resultaQuery.getString("nome"));
				professor.setSexo(resultaQuery.getBytes("sexo").toString().charAt(0));
				professor.setIdade(resultaQuery.getInt("idade"));
				professor.setQualificacao(resultaQuery.getString("qualificacao"));
				professores.add(professor);
			}
			return professores;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Professor> findByDisciplina(Disciplina disciplina) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from projetoaulas.professor p"
					+ "left join projetoaulas.disciplina d on (p.id = d.id_Professor)";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setString(1, disciplina.getNome());
			ResultSet resultaQuery = statement.executeQuery();
			List<Professor> professores = new ArrayList<>();
			while (resultaQuery.next()) {
				Professor professor = new Professor();
				professor.setId(resultaQuery.getInt("id"));
				professor.setNome(resultaQuery.getString("nome"));
				professor.setSexo(resultaQuery.getBytes("sexo").toString().charAt(0));
				professor.setIdade(resultaQuery.getInt("idade"));
				professor.setQualificacao(resultaQuery.getString("qualificacao"));
				professores.add(professor);
			}
			return professores;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Professor findByUsuarioId(int id) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			Usuario usuario = new Usuario();
			String sql = "select * from projetoaulas.professor p"
					+ "left join projetoaulas.usuario u on (u.id = p.id_Usuario)";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, usuario.getId());
			ResultSet resultaQuery = statement.executeQuery();
			while (resultaQuery.next()) {
				Professor professor = new Professor();
				professor.setId(resultaQuery.getInt("id"));
				professor.setNome(resultaQuery.getString("nome"));
				professor.setSexo(resultaQuery.getCharacterStream("sexo").toString().charAt(0));
				professor.setIdade(resultaQuery.getInt("idade"));
				professor.setQualificacao(resultaQuery.getString("qualificacao"));
				return professor;

			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
