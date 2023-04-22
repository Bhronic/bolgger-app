package com.blogger.dao;

import java.util.List;

import com.blogger.model.User;

public interface UserDao {
	
	public List<User> getUserList(int pageNo, int limit);
	
	public int getCount();

}
