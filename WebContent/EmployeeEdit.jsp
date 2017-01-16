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
<h2>Edit User Info</h2>
<span id="error">${errorMsg}</span>
<form action="EmpEditSubSrvlt" method="post">
	<input type="text" name="name" value="${uName}"/><br><sup>Username (3-16 characters; no spaces)</sup><br><br>
    <input type="password" name="pass" value="${pass}"/><br><sup>Password (8-16 characters)</sup><br><br>
    <input type="password" name="confirm" /><br><sup>Confirm Password</sup><br><br>
    <input type="text" name="em" value="${email}"/><br><sup>E-mail</sup><br><br>
    <input type="submit" value="Submit Profile Edit" name="sub">  <input type="submit" value="Cancel" name="sub">  
</form>
</div>
</div>
<div id="footer">
Copyright © Team One - CSC 481
</div>

</body>
</html>

