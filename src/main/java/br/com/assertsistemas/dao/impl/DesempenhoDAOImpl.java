package br.com.assertsistemas.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.assertsistemas.dao.DesempenhoDAO;
import br.com.assertsistemas.entity.Aluno;
import br.com.assertsistemas.entity.Desempenho;
import br.com.assertsistemas.entity.Disciplina;
import br.com.assertsistemas.entity.Status;

public class DesempenhoDAOImpl implements DesempenhoDAO {

	Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();

	@Override
	public Desempenho findByAlunoId(int id) {
		try {
			Aluno aluno = new Aluno(id, null);
			String sql = "select * from projetoaulas.aluno a"
					+ "left join projetoaulas.desempenho d on (a.id = d.id_Aluno)";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, aluno.getId());
			ResultSet resultaQuery = statement.executeQuery();
			while (resultaQuery.next()) {
				Desempenho desempenho = new Desempenho();
				desempenho.setId(resultaQuery.getInt("id"));
				desempenho.setNota1(resultaQuery.getDouble("nota1"));
				desempenho.setNota2(resultaQuery.getDouble("nota2"));
				desempenho.setNota3(resultaQuery.getDouble("nota3"));
				desempenho.setMedianota(resultaQuery.getDouble("mediaNota"));
				return desempenho;

			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Desempenho findByDisciplinaId(int id) {
		try {
			Disciplina disciplina = new Disciplina();
			String sql = "select * from projetoaulas.disciplina d"
					+ "left join projetoaulas.desempenho de on (d.id = de.id_Disciplina)";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, disciplina.getId());
			ResultSet resultaQuery = statement.executeQuery();
			while (resultaQuery.next()) {
				Desempenho desempenho = new Desempenho();
				desempenho.setId(resultaQuery.getInt("id"));
				desempenho.setNota1(resultaQuery.getDouble("nota1"));
				desempenho.setNota2(resultaQuery.getDouble("nota2"));
				desempenho.setNota3(resultaQuery.getDouble("nota3"));
				desempenho.setMedianota(resultaQuery.getDouble("medianota"));
				return desempenho;

			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean insert(Desempenho desempenho) {
		try {
			String sql = "insert into desempenho (mediaNota, nota1, nota2, nota3) values (?,?,?,?)";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, desempenho.getId());
			statement.setDouble(2, desempenho.getNota1());
			statement.setDouble(3, desempenho.getNota2());
			statement.setDouble(4, desempenho.getNota3());
			statement.setDouble(5, desempenho.getMedianota());
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
	public boolean update(Desempenho desempenho) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "UPDATE nota SET nota1=?, nota2=?, nota3=?, mediaNota=?, WHERE id=?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, desempenho.getId());
			statement.setDouble(2, desempenho.getNota1());
			statement.setDouble(3, desempenho.getNota2());
			statement.setDouble(4, desempenho.getNota3());
			statement.setDouble(5, desempenho.getMedianota());
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
	public boolean delete(int desempenhoId) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "delete from nota where id = ?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, desempenhoId);

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
	public List<Desempenho> findAll() {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from nota";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			List<Desempenho> desempenhos = new ArrayList<>();
			ResultSet resultaQuery = statement.executeQuery();
			while (resultaQuery.next()) {
				Desempenho desempenho = new Desempenho();
				desempenho.setId(resultaQuery.getInt("id"));
				desempenho.setNota1(resultaQuery.getDouble("nota1"));
				desempenho.setNota2(resultaQuery.getDouble("nota2"));
				desempenho.setNota3(resultaQuery.getDouble("nota3"));
				desempenho.setMedianota(resultaQuery.getDouble("medianota"));

				desempenhos.add(desempenho);
			}
			return desempenhos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Desempenho findById(int notaId) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from  desempenho where id = ?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, notaId);

			ResultSet resultaQuery = statement.executeQuery();

			while (resultaQuery.next()) {
				Desempenho desempenho = new Desempenho();
				desempenho.setId(resultaQuery.getInt("id"));
				desempenho.setNota1(resultaQuery.getDouble("nota1"));
				desempenho.setNota2(resultaQuery.getDouble("nota2"));
				desempenho.setNota3(resultaQuery.getDouble("nota3"));
				desempenho.setMedianota(resultaQuery.getDouble("medianota"));
				return desempenho;
			}

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Desempenho> findByStatus(Status status) {

		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from desempenho where status = ?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setString(1, status.name());
			ResultSet resultaQuery = statement.executeQuery();
			List<Desempenho> desempenhos = new ArrayList<>();
			while (resultaQuery.next()) {
				Desempenho desempenho = new Desempenho();
				desempenho.setId(resultaQuery.getInt("id"));
				desempenho.setNota1(resultaQuery.getDouble("Nota1"));
				desempenho.setNota2(resultaQuery.getDouble("Nota2"));
				desempenho.setNota3(resultaQuery.getDouble("Nota3"));
				desempenho.setMedianota(resultaQuery.getDouble("Medianota"));
				desempenhos.add(desempenho);
			}
			return desempenhos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}