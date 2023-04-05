package com.blogger.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserImage {
	public static void main(String args[]) throws Exception {

		// register driver
		Class.forName("com.mysql.cj.jdbc.Driver");

		// connection establish (open connection)
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blog_db", "root", "admin@123");

		PreparedStatement ps = con.prepareStatement("insert into user_image values(?,?,?)");
		ps.setInt(1, 0);
		ps.setString(2, "hitesh");

		FileInputStream fis = new FileInputStream("D:\\demo-img-x.jpg");
		ps.setBinaryStream(3, fis, fis.available());

		int val = ps.executeUpdate();
		System.out.println(val + " record inserted.");
		
		PreparedStatement ps1 = con.prepareStatement("select * from user_image");
		ResultSet rs = ps1.executeQuery();
		int i = 1;
		while (rs.next()) {
			
			Blob imge = rs.getBlob(3);
			byte[] b = imge.getBytes(1,(int) imge.length());
			
			FileOutputStream fio = new FileOutputStream("d:\\img-"+i+".jpg");
			fio.write(b);
			i++;
		}
		
		System.out.println("image store in d drive");
		
		con.close();
	}
}
