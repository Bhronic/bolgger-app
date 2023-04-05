package com.blogger.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class ExampleOfPreparedSt {
	public static void main(String args[]) {
		try {
			// register driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// connection establish (open connection)
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blog_db", "root", "admin@123");
			
			DatabaseMetaData dbmd=con.getMetaData();  
			  
			System.out.println("Driver Name: "+dbmd.getDriverName());  
			System.out.println("Driver Version: "+dbmd.getDriverVersion());  
			System.out.println("UserName: "+dbmd.getUserName());  
			System.out.println("Database Product Name: "+dbmd.getDatabaseProductName());  
			System.out.println("Database Product Version: "+dbmd.getDatabaseProductVersion());  
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			do {
				System.out.println("Do you want to continue: y/n");
				String s = br.readLine();
				if (s.startsWith("n")) {
					break;
				}
				
				System.out.println("Enter name");
				String name = br.readLine();
				System.out.println("Enter email");
				String email = br.readLine();
				System.out.println("Enter age");
				int age = Integer.parseInt(br.readLine());
				System.out.println("Enter mobile_no");
				String mobile_no = br.readLine();
				System.out.println("Enter password");
				String password = br.readLine();

				// insert record
				PreparedStatement ps = con.prepareStatement("insert into user values(?,?,?,?,?,?)");
				ps.setInt(1, 0);
				ps.setString(2, name + "-ps");
				ps.setString(3, email);
				ps.setInt(4, age);
				ps.setString(5, password);
				ps.setString(6, mobile_no);

				int value = ps.executeUpdate();
				System.out.println(value + " record inserted");

				

			} while (true);

			PreparedStatement ps1 = con.prepareStatement("update user set name=? where id=?");
			ps1.setString(1, "hitesh");
			ps1.setInt(2, 7);

			int updateValue = ps1.executeUpdate();
			System.out.println(updateValue + " record updated");

			PreparedStatement ps2 = con.prepareStatement("delete from user where id=?");
			ps2.setInt(1, 8);

			int deleteValue = ps2.executeUpdate();
			System.out.println(deleteValue + " record deleted");

			ResultSet resultSet = ps2.executeQuery("select * from user");
			ResultSetMetaData rsmt = (ResultSetMetaData) resultSet.getMetaData();
			
			System.out.println(rsmt.getColumnCount());
			System.out.println(rsmt.getColumnName(2));
			
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
