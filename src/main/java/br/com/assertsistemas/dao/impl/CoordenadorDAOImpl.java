package br.com.assertsistemas.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import br.com.assertsistemas.dao.CoordenadorDAO;
import br.com.assertsistemas.dao.UsuarioDAO;
import br.com.assertsistemas.entity.Coordenador;
import br.com.assertsistemas.entity.Desempenho;

public class CoordenadorDAOImpl implements CoordenadorDAO {

	private Connection connectionJDBC;

	public CoordenadorDAOImpl() {
		connectionJDBC = new ConnectJDBC().getConnectJDBC();
	}

	@Override
	public Coordenador findByUsuarioId(int id) {
		try {
			String sql = "select * from projetoaulas.coordenador c left join projetoaulas.usuario u on (u.id = c.id_usuario)";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			ResultSet resultaQuery = statement.executeQuery();
		//	statement.setInt(1, usuario.getId());
			while (resultaQuery.next()) {
				Coordenador coordenador = new Coordenador();
				coordenador.setId(resultaQuery.getInt("id"));
				coordenador.setNome(resultaQuery.getString("nome"));
				coordenador.setSexo(resultaQuery.getCharacterStream("sexo").toString().charAt(0));
				coordenador.setIdade(resultaQuery.getInt("idade"));
			//	coordenador.setUsuario(coordenador.getUsuario().getId());
				return coordenador;

			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Integer insert(Coordenador coordenador, int usuarioId) throws SQLException {

		String sql = "insert into coordenador (nome, sexo, idade, id_Usuario) values (?,?,?,?)";

		PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, coordenador.getNome());
		statement.setInt(2, coordenador.getSexo());
		statement.setInt(3, coordenador.getIdade());
		statement.setInt(4, coordenador.getUsuario().getId());

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("O Insert foi inserido com sucesso!");
		} else {
			throw new SQLException("Nao foi inserido o Coordenador");
		}

		ResultSet generatedKeys = statement.getGeneratedKeys();
		if (generatedKeys.next()) {
			return generatedKeys.getInt(1);
		} else {
			throw new SQLException("Id Disciplina ocoreu um erro");
		}
	}
		

	@Override
	public boolean update(Coordenador coordenador) {
		try {
			String sql = "UPDATE coordenador SET nome=?, sexo=?, idade=?, WHERE id=?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, coordenador.getId());
			statement.setString(2, coordenador.getNome());
			statement.setInt(3, coordenador.getSexo());
			statement.setInt(4, coordenador.getIdade());
			boolean updatesucess = false;
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Os dados já existentes foram atualizados com sucesso!");
			}
			updatesucess = true;
			return updatesucess;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int coordenadorId) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "delete from coordenador where id = ?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, coordenadorId);

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
	public List<Coordenador> findAll() {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from coordenador";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			List<Coordenador> coordenadores = new ArrayList<>();
			ResultSet resultaQuery = statement.executeQuery();
			while (resultaQuery.next()) {
				Coordenador coordenador = new Coordenador();
				coordenador.setId(resultaQuery.getInt("id"));
				coordenador.setNome(resultaQuery.getString("nome"));
				coordenador.setSexo(resultaQuery.getCharacterStream("sexo").toString().charAt(0));
				coordenador.setIdade(resultaQuery.getInt("idade"));
				coordenadores.add(coordenador);
			}
			return coordenadores;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Coordenador findById(int coordenadorId) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from coordenador where id = ?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setInt(1, coordenadorId);
			ResultSet resultaQuery = statement.executeQuery();
			while (resultaQuery.next()) {
				Coordenador coordenador = new Coordenador();
				coordenador.setId(resultaQuery.getInt("id"));
				coordenador.setNome(resultaQuery.getString("nome"));
				coordenador.setSexo(resultaQuery.getCharacterStream("sexo").toString().charAt(0));
				coordenador.setIdade(resultaQuery.getInt("idade"));
				return coordenador;
			}

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Coordenador findByDesempenho(Desempenho desempenho) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from coordenador where desempenho = ?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setObject(1, desempenho.getStatus());
			ResultSet resultaQuery = statement.executeQuery();
			List<Desempenho> desempenhos = new ArrayList<>();
			while (resultaQuery.next()) {
				desempenho.setId(resultaQuery.getInt("id"));
				desempenho.setNota1(resultaQuery.getDouble("Nota 1"));
				desempenho.setNota2(resultaQuery.getDouble("Nota 2"));
				desempenho.setNota3(resultaQuery.getDouble("Nota 3"));
				desempenhos.add(desempenho);
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) throws SQLException {
		Coordenador coordenador = new Coordenador();
		coordenador.setNome("Marcos");
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
		int usuarioId = usuarioDAO.findById(1);
		CoordenadorDAO coordenadorDAO = new CoordenadorDAOImpl();
		Integer c = coordenadorDAO.insert(coordenador, usuarioId);
		System.out.println(c.toString());
		
		
	}

}