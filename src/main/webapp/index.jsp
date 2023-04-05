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
<body>
	<center>
		<form action="home" method="post">
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
						placeholder="Enter user name"></td>
				</tr>
				<tr>
					<td><label for="lname">Password:</label></td>
				</tr>
				<tr>
					<td><input type="text" name="password"
						placeholder="Enter password"></td>
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
</html>