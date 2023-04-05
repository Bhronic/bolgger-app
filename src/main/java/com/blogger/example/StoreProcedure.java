package com.blogger.example;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class StoreProcedure {

	public static void main(String args[]) throws Exception {
		// register driver
		Class.forName("com.mysql.cj.jdbc.Driver");

		// connection establish (open connection)
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blog_db", "root", "admin@123");

		CallableStatement cs = con.prepareCall("call insert_user(?,?,?,?,?)");
		cs.setString(1, "moon");
		cs.setString(2, "moon@gmail.com");
		cs.setInt(3, 24);
		cs.setString(4, "admin@123");
		cs.setString(5, "8899776655");

		cs.execute();
		System.out.println("record inserted");
	}
}
