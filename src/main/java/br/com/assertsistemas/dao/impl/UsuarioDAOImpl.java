package br.com.assertsistemas.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import br.com.assertsistemas.dao.UsuarioDAO;
import br.com.assertsistemas.entity.Tipo;
import br.com.assertsistemas.entity.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	@Override
	public Usuario findByLoginAndSenha(String login, String senha) {
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from usuario where login=? and senha=?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setObject(1, login);
			statement.setObject(2, senha);
			ResultSet resultaQuery = statement.executeQuery();
			if (resultaQuery.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultaQuery.getInt("id"));
				usuario.setLogin(resultaQuery.getString("login"));
				usuario.setSenha(resultaQuery.getString("senha"));
				usuario.setTipoUsuario(Tipo.findTipo(resultaQuery.getString("tipo_usuario")));
				return usuario;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("null")
	public int findById(int id) {
		
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "select * from usuario where id=?";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql);
			statement.setObject(1, id);
			ResultSet resultaQuery = statement.executeQuery();
			if (resultaQuery.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultaQuery.getInt("id"));
				usuario.setLogin(resultaQuery.getString("login"));
				usuario.setSenha(resultaQuery.getString("senha"));
				usuario.setTipoUsuario(Tipo.findTipo(resultaQuery.getString("tipo_usuario")));
				return id;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Integer) null;
		
		
	}

	public Integer insert(Usuario usuario) {

		Integer usuarioId = null;
		try {
			Connection connectionJDBC = new ConnectJDBC().getConnectJDBC();
			String sql = "insert into usuario (login, senha, tipo_usuario) values (?,?,?)";
			PreparedStatement statement = (PreparedStatement) connectionJDBC.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, usuario.getTipoUsuario().name());
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {

				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						usuarioId = generatedKeys.getInt(1);
					} else {
						throw new SQLException("Creating user failed, no ID obtained.");
					}
				}
			}

			System.out.println("O Insert foi inserido com sucesso!");
			connectionJDBC.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarioId;
	}
}
