<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>	
<center>	
<h3>Login Page</h3>
	<form:form action="loginSave" method="GET" modelAttribute="customer">
		<table cellpadding="2" cellspacing="10" >
			<tr><td colspan="2" align="center">PAYTM</td></tr>
			<tr><td>Enter Mobile No:</td>
				<td><input type="text" name="mobno" /></td>
			</tr>
			<tr>
            	<td><input name="submit" type="submit" value="Login" /></td>
         	</tr>
		</table>			
	</form:form>
</center>
<div align="right"><a href="/CGWalletApp/">Home Page</a></div>
</body>
</html>