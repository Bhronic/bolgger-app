package com.blogger.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blogger.dbconnection.DbConnection;
import com.mysql.cj.util.StringUtils;

/**
 * Servlet implementation class Welcome
 */
@WebServlet("/home")
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Welcome() {
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
		response.setContentType("text/html");

		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("password"));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			Connection connection = new DbConnection().getConnection();
			PreparedStatement st = connection.prepareStatement("select * from user where emailid=? and password=?");
			st.setString(1, userName);
			st.setString(2, password);

			if (!validateUser(userName, password)) {
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				PrintWriter pr3 = response.getWriter();
				pr3.append("<html><center><b>user name & password are not null or empty.</b></center><html>");
				rd.include(request, response);
				
			}else {

			ResultSet resultSet = st.executeQuery();

			if (resultSet.next()) {
				System.out.println("call result set......");
				if (userName.equals(resultSet.getString("emailid"))
						& password.equals(resultSet.getString("password"))) {
					PrintWriter pr = response.getWriter();
					pr.append("<html>" + "<head>\r\n" + "<style>\r\n" + "table {\r\n"
							+ "  border-collapse: collapse;\r\n" + "  width: 100%;\r\n" + "}\r\n" + "\r\n"
							+ "th, td {\r\n" + "  text-align: left;\r\n" + "  padding: 8px;\r\n" + "}\r\n" + "\r\n"
							+ "tr:nth-child(even) {\r\n" + "  background-color: #D6EEEE;\r\n" + "}\r\n" + "body {\r\n"
							+ "	padding-left: 25%;\r\n" + "	padding-right: 25%;\r\n" + "}" + "</style>\r\n" + "</head>"
							+ "<body style=\"background: aliceblue;\"><table >");
					pr.append("<tr><td><b>welcome</b></td><td> " + userName + "</td></tr></table>");
					pr.append("<table>");
					pr.append("<tr><td>id</th><th>name</th><th>email id</th><th align= center colspan=2>action </th></tr>");
					
					PreparedStatement query = connection.prepareStatement("select * from user");
					ResultSet resultlist = query.executeQuery();
					while(resultlist.next()) {
					pr.append("<tr><td>"+resultlist.getString("id")+"</td><td>"+resultlist.getString("name")
					+"</td><td>"+resultlist.getString("emailid")
					+"</td><td><a href=edit?id="+ resultlist.getString("id")
					+">edit<a/</td><td><a href="+ resultlist.getString("id")+">delete</a></td></tr>");
					}
					
					pr.append("</table></body></html>");

					System.out.println(resultSet.getString("emailid") + ", " + resultSet.getString("password"));
				}
				
			}else {
				PrintWriter pr3 = response.getWriter();
				pr3.append("<html><center><b>user name & password are not valid.</b></center><html>");
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.include(request, response);
			}
			
			new DbConnection().closeConnection(connection);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	boolean validateUser(String userName, String password) {
		if (StringUtils.isNullOrEmpty(userName) & StringUtils.isNullOrEmpty(password)) {
			return false;
		}
		return true;
	}

}
