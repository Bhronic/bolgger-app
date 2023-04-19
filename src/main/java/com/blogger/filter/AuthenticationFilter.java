package com.blogger.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import com.blogger.dbconnection.DbConnection;
import com.mysql.cj.util.StringUtils;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/home")
public class AuthenticationFilter extends HttpFilter implements Filter {

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public AuthenticationFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
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

			} else {

				ResultSet resultSet = st.executeQuery();

				if (resultSet.next()) {

					chain.doFilter(request, response);
				} else {
					PrintWriter pr3 = response.getWriter();
					pr3.append("<html><center><b>user name & password are not valid.</b></center><html>");
					RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
					rd.include(request, response);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		// pass the request along the filter chain

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	boolean validateUser(String userName, String password) {
		if (StringUtils.isNullOrEmpty(userName) | StringUtils.isNullOrEmpty(password)) {
			return false;
		}
		return true;
	}

}
