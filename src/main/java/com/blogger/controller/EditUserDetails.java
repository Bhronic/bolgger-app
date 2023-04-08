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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userId = Integer.parseInt(request.getParameter("id"));
		try {
			Connection connection = new DbConnection().getConnection();
			PreparedStatement st;

			st = connection.prepareStatement("select * from user where id=?");
			st.setInt(1, userId);
			ResultSet resultSet = st.executeQuery();

			if (resultSet.next()) {
				PrintWriter pr = response.getWriter();
				pr.append("<html>\r\n" + "<head>\r\n" + "<meta charset=\"ISO-8859-1\">\r\n" + "<title>login</title>\r\n"
						+ "<head>\r\n" + "<style>\r\n" + "table {\r\n" + "	border-collapse: collapse;\r\n"
						+ "	width: 100%;\r\n" + "}\r\n" + "\r\n" + "th, td {\r\n" + "	text-align: left;\r\n"
						+ "	padding: 8px;\r\n" + "}\r\n" + "\r\n" + "tr:nth-child(even) {\r\n"
						+ "	background-color: #D6EEEE;\r\n" + "}\r\n" + "\r\n" + "input {\r\n" + "	width: 100%;\r\n"
						+ "}\r\n" + "\r\n" + "body {\r\n" + "	padding-left: 25%;\r\n" + "	padding-right: 25%;\r\n"
						+ "}\r\n" + "</style>\r\n" + "</head>\r\n" + "</head>\r\n" + "<body>\r\n" + "	<center>\r\n"
						+ "		<form action=\"edit\" method=\"post\">\r\n"
						+ "<input type=\"hidden\" id=\"custId\" name=\"id\" value=" + resultSet.getString(1) + ">"
						+ "			<table>\r\n" + "				<tr>\r\n"
						+ "					<td style=\"text-align: center;\"><label for=\"fname\">Edit user details\r\n"
						+ "							</label></td>\r\n" + "				</tr>\r\n"
						+ "				<tr>\r\n"
						+ "					<td><label for=\"fname\">User name:</label></td>\r\n"
						+ "				</tr>\r\n" + "				<tr>\r\n"
						+ "					<td><input type=\"text\" value=" + resultSet.getString(2)
						+ " name=\"username\"\r\n" + "						placeholder=\"Enter user name\"></td>\r\n"
						+ "				</tr>\r\n" + "				<tr>\r\n"
						+ "					<td><label for=\"lemailid\">Email Id:</label></td>\r\n"
						+ "				</tr>\r\n" + "				<tr>\r\n"
						+ "					<td><input type=\"text\" value=" + resultSet.getString(3)
						+ " name=\"emailId\"\r\n" + "						placeholder=\"Enter email id\"></td>\r\n"
						+ "				</tr>\r\n" + "				<tr>\r\n"
						+ "					<td><label for=\"lmob\">Age:</label></td>\r\n" + "				</tr>\r\n"
						+ "				<tr>\r\n" + "					<td><input type=\"text\" value="
						+ resultSet.getString(4) + " name=\"age\"\r\n"
						+ "						placeholder=\"Enter age\"></td>\r\n" + "				</tr>\r\n"
						+ "				<tr>\r\n"
						+ "					<td style=\"padding-left: 30%; padding-right: 30%;\"><input\r\n"
						+ "						style=\"background: lightgrey; border: aliceblue;\" type=\"submit\"\r\n"
						+ "						name=\"update\" value=\"edit user\"></td>\r\n"
						+ "				</tr>\r\n" + "			</table>\r\n" + "		</form>\r\n"
						+ "	</center>\r\n" + "</body>\r\n" + "</html>");
				connection.close();

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
