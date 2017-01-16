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
<<<<<<< HEAD
  <li><a href="PublicationCenter.jsp">Publications</a></li>
  <li><a href="Printing.jsp">Printing</a></li>
  <ul style="float:right;list-style-type:none;">
  <li><a class="active" href="EmployeeHome.jsp"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
=======
  <li><a class="active" href="PublicationCenter.jsp">Publications</a></li>
  <li><a href="Printing.jsp">Printing</a></li>
  <ul style="float:right;list-style-type:none;">
  <li><a href="EmployeeHome.jsp"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
  <li><a href="Login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
</ul>
</div>

<<<<<<< HEAD

<div id="bckgrnd">
<div id="section">
<h2>Publication Information</h2>
<span id="message">${msg}<br></span>
<form action="PubEditSrvlt" method="post">
	<table>
		<tr>
			<td>Publication ID: </td>
			<td>${PID}</td>
		</tr>
		<tr>
			<td>Publication Name: </td>
			<td>${pName}</td>
		</tr>
		<tr>
			<td>Genre: </td>
    		<td>${genre}</td>
  		</tr>
  		<tr><td></td><td></td><td style="text-align: right"><input type="submit" value="Edit Name" name = "subm"></td></tr>
	</table>
	<br>
	<table>
		<tr>
			<td>Price: </td>
    		<td>${price}</td>
  		</tr>
  		<tr>
			<td>Frequency: </td>
    		<td>${frequency}</td>
  		</tr>
  		  <tr>
			<td>Delivery Days: </td>
    		<td>${ddays}</td>
  		</tr>
  		<tr>
			<td>Status: </td>
    		<td>${status}</td>
  		</tr>
  		<tr><td></td><td></td><td style="text-align: right"><input type="submit" value="Edit Number" name = "subm"></td></tr>
	</table>
	<br>
	<input type="submit" value="Back to search" name="subm">
</form>
</div>
<script type="text/javascript">
	function replace(){
	
	}

</script>
=======
<div id="bckgrnd">
<div id="section">
<<<<<<< HEAD
<<<<<<< HEAD
=======
<h1>Modify Publications</h1>
>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
</div>
=======
>>>>>>> master
<h2>Publication Information</h2>
<span id="message">${msg}<br></span>
<form action="PubEditSrvlt" method="post">
	<table>
		<tr>
			<td>Publication ID: </td>
			<td>${PID}</td>
		</tr>
		<tr>
			<td>Publication Name: </td>
			<td>${pName}</td>
		</tr>
		<tr>
			<td>Genre: </td>
    		<td>${genre}</td>
  		</tr>
  		<tr><td></td><td></td><td style="text-align: right"><input type="submit" value="Edit Name" name = "subm"></td></tr>
	</table>
	<br>
	<table>
		<tr>
			<td>Price: </td>
    		<td>${price}</td>
  		</tr>
  		<tr>
			<td>Frequency: </td>
    		<td>${frequency}</td>
  		</tr>
  		  <tr>
			<td>Delivery Days: </td>
    		<td>${ddays}</td>
  		</tr>
  		<tr>
			<td>Status: </td>
    		<td>${status}</td>
  		</tr>
  		<tr><td></td><td></td><td style="text-align: right"><input type="submit" value="Edit Number" name = "subm"></td></tr>
	</table>
	<br>
	<input type="submit" value="Back to search" name="subm">
</form>
<<<<<<< HEAD
=======
<h1>Modify Publications</h1>
</div>
>>>>>>> master
=======
>>>>>>> Nathan_Branch
>>>>>>> master
</div>
<script type="text/javascript">
	function replace(){
	
	}

</script>

<div id="footer">
Copyright © Team One - CSC 481
</div>

</body>
</html>

