package me.cqp.samarium;

import java.sql.*;

class SQLModifier {
	private static final String JDBC_Driver = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/Maid";
	
	private static final String USER = "root";
	private static final String PASSWORD = "zSriQNwe6RFrBcV";
	
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	
	SQLModifier(String sql){
		try {
			Class.forName(JDBC_Driver);
			
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
		}catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (connection!=null) connection.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (statement!=null) statement.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ResultSet getResultSet() {return resultSet;}
	
}
