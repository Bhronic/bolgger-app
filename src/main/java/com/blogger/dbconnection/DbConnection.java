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

	public List<User> getUserList(int pageNo, int limit) {
		List<User> list = new ArrayList<User>();
		try {
			PreparedStatement query = getConnection()
					.prepareStatement("select * from user limit " + (pageNo * limit) + "," + limit);
			ResultSet resultlist = query.executeQuery();

			while (resultlist.next()) {
				User user = new User(resultlist.getInt(1), resultlist.getString(2), resultlist.getString(3),
						resultlist.getString(4), resultlist.getString(5));
				list.add(user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public int getCount() {
		int count = 0;

		try {
			PreparedStatement query = getConnection().prepareStatement("select count(*) from user");
			ResultSet resultlist = query.executeQuery();
			if (resultlist.next()) {
				count = resultlist.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
}
