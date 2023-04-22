package com.blogger.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.blogger.model.User;

public class DbConnection {

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		// register driver
		Class.forName("com.mysql.cj.jdbc.Driver");

		// connection establish (open connection)
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blog_db", "root", "admin@123");
		return con;
	}

	public void closeConnection(Connection con) throws SQLException {
		con.close();
	}

}
