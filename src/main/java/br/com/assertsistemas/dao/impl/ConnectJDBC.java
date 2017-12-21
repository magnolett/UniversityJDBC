package br.com.assertsistemas.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class ConnectJDBC {

	private String host = "jdbc:mysql://localhost:3306/projetoaulas";
	private String user = "root";
	private String senha = "root";
	private Connection connection;

	public ConnectJDBC() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(host, user, senha);
			System.out.println("Conectado");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnectJDBC() {
		return this.connection;
	}

	public static PreparedStatement prepareStatement(String sql) {
		return null;
	}
}
