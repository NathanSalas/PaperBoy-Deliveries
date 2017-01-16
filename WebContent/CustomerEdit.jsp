<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<head>
<link rel="stylesheet" type="text/css" href="header.css">
</head>

<body>
<div id="header">
</div>

<div id="nav">
<ul>
  <li><a href="index.jsp"><span class="glyphicon glyphicon-home"></span> Home</a></li>  
<<<<<<< HEAD
  <li><a href="CustomerCenter.jsp">Customers</a></li>
  <li><a href="PublicationCenter.jsp">Publications</a></li>
  <li><a href="Printing.jsp">Printing</a></li>
  <ul style="float:right;list-style-type:none;">
  <li><a class="active" href="EmployeeHome.jsp"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
=======
  <li><a class="active" href="CustomerCenter.jsp">Customers</a></li>
  <li><a href="PublicationCenter.jsp">Publications</a></li>
  <li><a href="Printing.jsp">Printing</a></li>
  <ul style="float:right;list-style-type:none;">
  <li><a href="EmployeeHome.jsp"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
  <li><a href="Login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
</ul>
</div>

<<<<<<< HEAD

=======
>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
<div id="bckgrnd">
<div id="section">
<h2> Customer Profile</h2>
<span id="message">${msg}<br></span>
<form action="CustEditSrvlt" method="post">
	<table>
		<tr>
			<td>Customer ID: </td>
			<td>${CID}</td>
		</tr>
		<tr>
			<td>First Name: </td>
			<td>${firstName}</td>
		</tr>
		<tr>
			<td>Last Name: </td>
    		<td>${lastName}</td>
  		</tr>

		<tr>
			<td>Phone Number: </td>
    		<td>${phone}</td>
  		</tr>
		<tr>
  			<td>Street Address: </td>
  			<td>${address}</td>
  		</tr>
  		<tr>
  			<td>Street Address 2: </td>
  			<td>${addr2}</td>
  		</tr>
  		<tr>
  			<td>City: </td>
  			<td>${city}</td>
  		</tr>
  		<tr>
  			<td>State: </td>
  			<td>${state}</td>
  		</tr>
  		<tr>
  			<td>Zip: </td>
  			<td>${zip}</td>
  		</tr>
	</table>
	<input type="hidden" name="CID" value="${CID}">
	<input type="hidden" name="firstName" value="${firstName}">
	<input type="hidden" name="lastName" value="${lastName}">
	<input type="submit" value="Edit Customer Details" name="add"><br><br>
	<input type="submit" value="Edit Customer Subscriptions" name="add">
</form>
<<<<<<< HEAD

=======
>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
</div>
</div>
<div id="footer">
Copyright © Team One - CSC 481
</div>

</body>
</html>

