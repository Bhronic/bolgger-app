package com.blogger.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogger.dbconnection.DbConnection;

/**
 * Servlet implementation class deleteUser
 */
@WebServlet("/delete")
public class deleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int id = Integer.parseInt(request.getParameter("id"));

		HttpSession session = request.getSession(false);
		String sessionEmailid = (String) session.getAttribute("emailId");
		if (null != sessionEmailid) {
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			PrintWriter pr3 = response.getWriter();
			pr3.append("<html><center><b>You have to login first.</b></center><html>");
			rd.include(request, response);
		}

		try {
			Connection connection = new DbConnection().getConnection();
			PreparedStatement st;

			st = connection.prepareStatement("delete from user where id=?");
			st.setInt(1, id);
			int resultSet = st.executeUpdate();
			if (resultSet != 0) {
				response.sendRedirect("/blogger_app/home?username=" + sessionEmailid);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
