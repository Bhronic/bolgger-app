<%@page import="java.util.Optional"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.blogger.dbconnection.DbConnection"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login</title>
<head>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #D6EEEE;
}

input {
	width: 100%;
}

body {
	padding-left: 25%;
	padding-right: 25%;
}
</style>
</head>
</head>
<%
String sessionEmailid = (String) session.getAttribute("emailId");
if (Optional.ofNullable(sessionEmailid).isEmpty()) {
	RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
	PrintWriter pr3 = response.getWriter();
	pr3.append("<html><center><b>You have to login first.</b></center><html>");
	rd.include(request, response);
}
int userId = Integer.parseInt(request.getParameter("id"));
try {
	Connection connection = new DbConnection().getConnection();
	PreparedStatement st;

	st = connection.prepareStatement("select * from user where id=?");
	st.setInt(1, userId);
	ResultSet resultSet = st.executeQuery();

if(resultSet.next()){
%>
<body>
	<center>
		<form action="edit" method="post">
			<input type="hidden" name="id" value=<%=resultSet.getString(1) %>>

			<table>
				<tr>
					<td style="text-align: center;"><label for="fname">Login
							form</label></td>
				</tr>
				<tr>
					<td><label for="fname">User name:</label></td>
				</tr>
				<tr>
					<td><input type="text" name="username"
						value="<%=resultSet.getString(2) %>" placeholder="Enter user name"></td>
				</tr>
				<tr>
					<td><label for="lname">Email Id:</label></td>
				</tr>
				<tr>
					<td><input type="text" name="emailId"
						value=<%=resultSet.getString(3) %> placeholder="Enter Emailid"></td>
				</tr>

				<tr>
					<td><label for="lname">Age:</label></td>
				</tr>
				<tr>
					<td><input type="text" name="age"
						value=<%=resultSet.getString(4) %> placeholder="Enter age"></td>
				</tr>
				<tr>
					<td style="padding-left: 30%; padding-right: 30%;"><input
						style="background: lightgrey; border: aliceblue;" type="submit"
						name="login" value="login"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
<%
}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
%>
</html>