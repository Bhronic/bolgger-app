package com.blogger.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogger.dbconnection.DbConnection;

/**
 * Servlet implementation class EditUserDetails
 */
@WebServlet("/edit")
public class EditUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String userName = request.getParameter("username");
		int id = Integer.parseInt(request.getParameter("id"));
		String age = request.getParameter("age");
		String emailId = request.getParameter("emailId");

		System.out.println(userName + "," + id + "," + age + "," + emailId);

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

			st = connection.prepareStatement("update user set name=?, emailid=?, age=? where id=?");
			st.setString(1, userName);
			st.setString(2, emailId);
			st.setString(3, age);
			st.setInt(4, id);
			int resultSet = st.executeUpdate();
			if (resultSet != 0) {
				PrintWriter pr3 = response.getWriter();
				pr3.append("<html><center><b>" + userName + " detail update successfully</b></center><html>");
//				RequestDispatcher rd = request.getRequestDispatcher("/home?username="+userName);
//				rd.forward(request, response);
				response.sendRedirect("/blogger_app/home?username=" + sessionEmailid);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
