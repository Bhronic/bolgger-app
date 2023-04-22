package com.blogger.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.blogger.dao.UserDao;
import com.blogger.dbconnection.DbConnection;
import com.blogger.model.User;

public class UserDaoImpl implements UserDao{
	
	private DbConnection connection = new DbConnection();

	public List<User> getUserList(int pageNo, int limit) {
		List<User> list = new ArrayList<User>();
		try {
			PreparedStatement query = connection.getConnection()
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
			PreparedStatement query = connection.getConnection().prepareStatement("select count(*) from user");
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
