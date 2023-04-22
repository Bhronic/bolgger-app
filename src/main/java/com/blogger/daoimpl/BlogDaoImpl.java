package com.blogger.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.blogger.dao.BlogDao;
import com.blogger.dbconnection.DbConnection;
import com.blogger.model.Blog;

public class BlogDaoImpl implements BlogDao {

	private DbConnection connection = new DbConnection();

	@Override
	public int addBlog(Blog blog) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = connection.getConnection().prepareStatement(
				"INSERT INTO `blog_db`.`blog` (`user_name`, `blog_title`, `blog_content`) VALUES (?, ?, ?);");
		preparedStatement.setString(1, blog.getUsername());
		preparedStatement.setString(2, blog.getBlogTitle());
		preparedStatement.setString(3, blog.getBlogContent());
		return preparedStatement.executeUpdate();
	}

	@Override
	public List<Blog> getBlogList(Blog blog) throws ClassNotFoundException, SQLException {
		PreparedStatement preparedStatement = connection.getConnection().prepareStatement("select * from`blog`");
		ResultSet resultSet = preparedStatement.executeQuery();
		List<Blog> bolgList = new ArrayList<Blog>();
		while (resultSet.next()) {
			Blog blogValue = new Blog(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4));
			bolgList.add(blogValue);
		}
		return bolgList;
	}

}
