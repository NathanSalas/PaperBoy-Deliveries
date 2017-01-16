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

<div id="bckgrnd">
=======
<div id="bckgrnd">

>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
<div id="section">
<h1>Lookup Customer</h1><br><br>
Please Search by name and select customer with customer ID.
<span id="error">${errorMsg}</span>
<form action="CustSearchSrvlt" method="post">
    <input type="text" name="n" value="${names}"/><input type="submit" value="Submit Name" name="sub">
    <br><sup>Search by name (first and/or last name).</sup><br><br>
    
</form>
<span>${results}</span>
</div>
</div>
<<<<<<< HEAD

</div>
</div>
=======
>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
<div id="footer">
Copyright © Team One - CSC 481
</div>

</body>
</html>
