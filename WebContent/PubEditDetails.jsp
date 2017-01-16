<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<<<<<<< HEAD
<title>Employee Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
=======
<title>Edit Profile</title>

>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
<head>
<link rel="stylesheet" type="text/css" href="header.css">
</head>

<body>
<<<<<<< HEAD
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
=======
<div id="header"></div>

<div id="nav">
<a href="EmployeeHome.jsp" style="text-decoration:none"><font color="white" size = "4"><b>Home</b></font></a><br><br>
<a href="CustomerCenter.jsp" style="text-decoration:none"><font color="white" size = "4"><b>Customer Center</b></font></a><br><br>
<a href="PublicationCenter.jsp" style="text-decoration:none"><font color="white" size = "4"><b>Publications</b></font></a><br><br>
<a href="Printing.jsp" style="text-decoration:none"><font color="white" size = "4"><b>Printing</b></font></a><br><br>
<a href="Login.jsp" style="text-decoration:none"><font color="white" size = "4"><b>Log Out</b></font></a><br><br>
</div>

>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
<div><h2>Edit Company Address</h2>
<span id="error">${errorMsg}</span>
<form action="PubEditSubSrvlt" method="post">
	
	<input type="text" name="Price" value="${price}"/><br><sup>Price</sup><br><br>
	<input type="text" name="Frequency" value="${frequency}"/><br><sup>Frequency</sup><br><br>
    <input type="text" name="Delivery Days" value="${ddays}"/><br><sup>Delivery Days</sup><br><br>
    <input type="text" name="Status" value="${status}"><br><sup>Status</sup><br><br>
    <input type="submit" value="Submit Name Edit" name="sub">  <input type="submit" value="Cancel" name="sub">
</form>
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