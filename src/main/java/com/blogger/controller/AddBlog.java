package com.blogger.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogger.dao.BlogDao;
import com.blogger.daoimpl.BlogDaoImpl;
import com.blogger.model.Blog;

/**
 * Servlet implementation class AddBlog
 */
@WebServlet("/addblog")
public class AddBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBlog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("blogTitle");
		String content = request.getParameter("blogContent");
		
		HttpSession session = request.getSession(false);
		String userName = (String) session.getAttribute("emailId");
		
		BlogDao blogDao = new BlogDaoImpl();
		try {
			blogDao.addBlog(new Blog(0, userName, title, content));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
