package me.cqp.samarium;

import java.sql.*;

public class SQLModifier {
	private final String URL;
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	SQLModifier(String url){
		this.URL = url;
		try {
			this.init();
		}catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void init() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(URL, USER, PASSWORD);
		statement = connection.createStatement();
	}
	
	void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	
	Statement getStatement() {
		return this.statement;
	}
	
	Connection getConnection() {
		return this.connection;
	}
	
	ResultSet getResultSet() {
		return this.resultSet;
	}
}
