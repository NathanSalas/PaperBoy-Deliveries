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
  <li><a href="EmployeeHome.jsp"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
  <li><a class ="active" href="Login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
</ul>
</div>

<div id="bckgrnd">
<div id= "section">
	<br><h1>Sign In</h1>
	<span id="error">${errorMsg}<br></span>
	<form action="LoginSrvlt" method="post">	
		<p class = "login">
			<input type="text" name="name" placeholder = "Username"/>
		</p>
		<p class = "login"> 
	    	<input type="password" name="pass" placeholder = "Password"/><br>
		</p>
		<p class = "login">
	    	<input type="submit" value="Login">
		</p>            
	</form>

</div>
</div>
<div id="footer">
Copyright © Team One - CSC 481
</div>

</body>
</html>