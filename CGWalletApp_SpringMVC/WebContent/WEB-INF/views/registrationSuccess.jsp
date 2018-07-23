<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>
</head>
<body>
	<div align="center">
		<h3>Welcome ${customer.name}</h3>
		<br>
		<h4>Mobile No ${customer.mobileNo}</h4>
		<h4>Wallet ${customer.wallet}</h4>
	  
	</div>
	<a href="login">Login</a>
	<div align="right"><a href="/CGWalletApp/">Home Page</a>		
	</div>
</body>
</html>