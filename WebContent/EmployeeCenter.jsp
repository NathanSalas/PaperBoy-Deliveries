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
  <li><a href="CustomerCenter.jsp">Customers</a></li>
  <li><a href="PublicationCenter.jsp">Publications</a></li>
  <li><a href="Printing.jsp">Printing</a></li>
  <ul style="float:right;list-style-type:none;">
  <li><a class="active" href="EmployeeHome.jsp"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
  <li><a href="Login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
</ul>
</div>


<div id="bckgrnd">
<div id="section">
<h2>Profile</h2>
<span id="message">${msg}<br></span>
<form action="EmpEditSrvlt" method="post">
	<table>
		<tr>
			<td>User Name: </td>
			<td>${uName}</td>
		</tr>
		<tr>
			<td>Password: </td>
    		<td>${hidPass}</td>
  		</tr>
  		<tr>
			<td>Password Recovery E-mail: </td>
    		<td>${email}</td>
  		</tr>
  		<tr><td></td><td></td><td style="text-align: right"><input type="submit" value="Edit User Info" name = "edit"></td></tr>
	</table>
	<br>
	<table>
		<tr>
  			<td>Company Address: </td>
  			<td>${company}</td>
  		</tr>
  		<tr>
  			<td></td>
  			<td>${uAddress}</td>
  		</tr>
  		<tr>
  			<td></td>
  			<td>${uCity}, ${uState} ${uZip}</td>
  		</tr>
  		<tr><td></td><td></td><td style="text-align: right"><input type="submit" value="Edit Address" name="edit"></td></tr>
	</table>
	<br>
	<table>
		<tr>
  			<td>Customer Service E-mail: </td>
  			<td>${csEmail}</td>
  		</tr>
  		<tr>
  			<td>Customer Service Phone: </td>
  			<td>${csPhone}</td>
  		</tr>
  		<tr><td></td><td></td><td style="text-align: right"><input type="submit" value="Edit Contact Info" name="edit"></td></tr>
	</table>
	<br>
	<table>
		<tr>
  			<td>File Path: </td>
  			<td>${path}</td>
  		</tr>
  		<tr><td></td><td></td><td style="text-align: right"><input type="submit" value="Edit Path" name="edit"></td></tr>
	</table>
</form>
<br>
</div>
</div>
<<<<<<< HEAD
=======

>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
<div id="footer">
Copyright © Team One - CSC 481
</div>

</body>
</html>

