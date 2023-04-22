package com.blogger.dao;

import java.sql.SQLException;
import java.util.List;

import com.blogger.model.Blog;

public interface BlogDao {
	
	public int addBlog(Blog blog) throws ClassNotFoundException, SQLException;
	
	public List<Blog> getBlogList(Blog blog) throws ClassNotFoundException, SQLException;
}
