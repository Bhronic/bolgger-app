package com.blogger.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogger.dao.UserDao;
import com.blogger.daoimpl.UserDaoImpl;
import com.blogger.dbconnection.DbConnection;
import com.blogger.model.User;
import com.mysql.cj.util.StringUtils;

/**
 * Servlet implementation class Welcome
 */
@WebServlet("/home")
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int limit = 4;

	private UserDao dao = new UserDaoImpl();

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

		try {

			HttpSession session = request.getSession(false);
			System.out.println("========>\nsession created time: " + session.getCreationTime());
			System.out.println("session access time: " + session.getLastAccessedTime());

			String sessionEmailId = (String) session.getAttribute("emailId");
			Optional.ofNullable(sessionEmailId);
			if (!Optional.ofNullable(sessionEmailId).isEmpty()) {

				String userName = request.getParameter("username");
				int pageNo = Integer.parseInt(request.getParameter("page"));

				Connection connection = new DbConnection().getConnection();
				PrintWriter pr = response.getWriter();
				pr.append("<html>" + "<head>\r\n" + "<style>\r\n" + "table {\r\n" + "  border-collapse: collapse;\r\n"
						+ "  width: 100%;\r\n" + "}\r\n" + "\r\n" + "th, td {\r\n" + "  text-align: left;\r\n"
						+ "  padding: 8px;\r\n" + "}\r\n" + "\r\n" + "tr:nth-child(even) {\r\n"
						+ "  background-color: #D6EEEE;\r\n" + "}\r\n" + "body {\r\n" + "	padding-left: 25%;\r\n"
						+ "	padding-right: 25%;\r\n" + "}" + "</style>\r\n" + "</head>"
						+ "<body style=\"background: aliceblue;\"><table >");
				pr.append("<tr><td><b>Welcome </b>" + sessionEmailId + "</td><td></td>"
						+ "<td><a href=logout><button>logout</button></a></td>" + "</tr></table>");
				pr.append("<table>");
				pr.append("<tr><td>id</th><th>name</th><th>email id</th><th align= center colspan=2>action </th></tr>");

				// user list
				getUserList(pr, pageNo, limit);

				pr.append("</table>");

				// Pagination
				addPageingUrl(pr, userName);

				pr.append("</body></html>");

			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				PrintWriter pr3 = response.getWriter();
				pr3.append("<html><center><b>You have to login first.</b></center><html>");
				rd.include(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
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

		try {
			Connection connection = new DbConnection().getConnection();

			// create new session
			HttpSession session = request.getSession();
			session.setAttribute("emailId", userName);
			System.out.println("========>\nsession created time: " + session.getCreationTime());
			System.out.println("session access time: " + session.getLastAccessedTime());

			if (session != null) {

				PrintWriter pr = response.getWriter();
				pr.append("<html>" + "<head>\r\n" + "<style>\r\n" + "table {\r\n" + "  border-collapse: collapse;\r\n"
						+ "  width: 100%;\r\n" + "}\r\n" + "\r\n" + "th, td {\r\n" + "  text-align: left;\r\n"
						+ "  padding: 8px;\r\n" + "}\r\n" + "\r\n" + "tr:nth-child(even) {\r\n"
						+ "  background-color: #D6EEEE;\r\n" + "}\r\n" + "body {\r\n" + "	padding-left: 25%;\r\n"
						+ "	padding-right: 25%;\r\n" + "}" + "</style>\r\n" + "</head>"
						+ "<body style=\"background: aliceblue;\"><table >");
				pr.append("<tr><td><b>Welcome </b> " + userName + "</td><td> </td>"
						+ "<td><a href=blog.jsp><button>Add Blog</button></a><a href=logout><button>logout</button></a></td>"
						+ "</tr></table>");
				pr.append("<table>");
				pr.append("<tr><td>id</th><th>name</th><th>email id</th><th align= center colspan=2>action </th></tr>");
				// user list
				getUserList(pr, 0, limit);

				pr.append("</table>");

				// Pagination
				addPageingUrl(pr, userName);

				pr.append("</body></html>");

			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				PrintWriter pr3 = response.getWriter();
				pr3.append("<html><center><b>You have to login first.</b></center><html>");
				rd.include(request, response);

			}

			new DbConnection().closeConnection(connection);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	boolean validateUser(String userName, String password) {
		if (StringUtils.isNullOrEmpty(userName) | StringUtils.isNullOrEmpty(password)) {
			return false;
		}
		return true;
	}

	public void getUserList(PrintWriter pr, int pageNo, int limit) {
		List<User> userList = dao.getUserList(pageNo, limit);
		for (User user : userList) {
			pr.append("<tr><td>" + user.getId() + "</td><td>" + user.getName() + "</td><td>" + user.getEmailId()
					+ "</td><td><a href=editUserDtls.jsp?id=" + user.getId() + ">edit<a/</td><td><a href=delete?id="
					+ user.getId() + "&username=" + user.getName() + ">delete</a></td></tr>");
		}
	}

	public void addPageingUrl(PrintWriter pr, String userName) {

		int noOfRecords = dao.getCount();
		int noPages = noOfRecords / limit;
		System.out.println("pages" + noPages);
		for (int i = 0; i <= noPages; i++) {
			pr.append("<a style=\"padding: 10;\" href=/blogger_app/home?username=" + userName + "&page=" + i + ">"
					+ (i + 1) + "</a>");
		}
	}

}
