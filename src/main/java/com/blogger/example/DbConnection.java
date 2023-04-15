package com.blogger.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnection {

	public static void main(String args[]) {
		try {
			// register driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// connection establish (open connection)
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blog_db", "root", "admin@123");

			// create statement
			Statement statement = con.createStatement();

			String name = "jaymin";
			String email = "jaymin@gmail.com";
			int age = 25;
			String mobile_no = "2345678976";
			String password = "admin@123";

			// insert record
			int recordInsert = statement.executeUpdate(
					"INSERT INTO `user` " + "(`name`, `emailid`, `age`, `password`, `contact_no`) " + "VALUES ('" + name
							+ "-st', '" + email + "', '" + age + "', '" + password + "', '" + mobile_no + "')");

			// delete record
			int deleteRecord = statement.executeUpdate("delete from user where id = 1");

			System.out.println(deleteRecord + " record is deleted");
			// Execute query
			ResultSet resultSet = statement.executeQuery("select * from user");

			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + ", " + resultSet.getString(2) + ", " + resultSet.getString(3));

			}

			// Close connection
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
