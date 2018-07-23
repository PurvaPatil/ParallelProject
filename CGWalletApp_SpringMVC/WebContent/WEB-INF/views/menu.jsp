<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu</title>
</head>
<body>
<%session.setAttribute("no", request.getParameter("mobno")); %>

	<div align="center">		
		<h3>You are successfully login to wallet app!!!</h3>
		<h4><b>Menu</b></h4>	
	<a href="showBalance">Show Balance</a><br>
	<a href="deposit">Deposit</a><br>
	<a href="withdraw">Withdraw</a><br>
	<a href="fundTransfer">FundTransfer</a><br>
	<a href="printTransaction">Print Transaction</a><br>
	</div>
	<div align="right"><a href="/CGWalletApp/">Home Page</a></div>
</body>
</html>