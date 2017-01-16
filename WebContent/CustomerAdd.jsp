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
  <li><a class="active" href="CustomerCenter.jsp">Customers</a></li>
  <li><a href="PublicationCenter.jsp">Publications</a></li>
  <li><a href="Printing.jsp">Printing</a></li>
  <ul style="float:right;list-style-type:none;">
  <li><a href="EmployeeHome.jsp"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
  <li><a href="Login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
</ul>
</div>

<div id="bckgrnd">

<div id="section">
<h2>Add a Customer</h2><br>
<span id="error">${errorMsg}<br></span>

<form action="CustAddSrvlt" method="post">
<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
=======
<<<<<<< HEAD
=======
>>>>>>> master
	<input type="text" name="first" value="${first}" placeholder = "First Name"/>
    <input type="text" name="last" value="${last}" placeholder = "Last Name"/><p>
    <input type="text" name="add1" value="${add1}" placeholder = "Address"/>
    <input type="text" name="add2" value="${add2}"placeholder = "Address 2"/>
    <input type="text" name="c" value="${c}"placeholder = "City"/><p>
    <select name="s" placeholder = "State">
<<<<<<< HEAD
=======
=======
>>>>>>> master
	<input type="text" name="first" value="${firstName}"/><br><sup>First Name</sup><p>
    <input type="text" name="last" value="${last}"/><br><sup>Last Name</sup><p>
    <input type="text" name="p" value="${p}"/><br><sup>Phone Number</sup><p>  
    <input type="text" name="add1" value="${add1}"/><br><sup>Street Address</sup><p>
    <input type="text" name="add2" value="${add2}"/><br><sup>Apartment, Unit, etc. (Optional)</sup><p>
    <input type="text" name="c" value="${c}"/><br><sup>City</sup><p>
    <select name="s">
<<<<<<< HEAD
=======
	<input type="text" name="first" value="${first}" placeholder = "First Name"/>
    <input type="text" name="last" value="${last}" placeholder = "Last Name"/><p>
    <input type="text" name="add1" value="${add1}" placeholder = "Address"/>
    <input type="text" name="add2" value="${add2}"placeholder = "Address 2"/>
    <input type="text" name="c" value="${c}"placeholder = "City"/><p>
    <select name="s" placeholder = "State">
>>>>>>> master
=======
>>>>>>> Nathan_Branch
<<<<<<< HEAD
>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
=======
>>>>>>> master
>>>>>>> master
    	<option selected="${s}">${s}</option>
		<option value="AL">AL</option>
		<option value="AK">AK</option>
		<option value="AZ">AZ</option>
		<option value="AR">AR</option>
		<option value="CA">CA</option>
		<option value="CO">CO</option>
		<option value="CT">CT</option>
		<option value="DE">DE</option>
		<option value="DC">DC</option>
		<option value="FL">FL</option>
		<option value="GA">GA</option>
		<option value="HI">HI</option>
		<option value="ID">ID</option>
		<option value="IL">IL</option>
		<option value="IN">IN</option>
		<option value="IA">IA</option>
		<option value="KS">KS</option>
		<option value="KY">KY</option>
		<option value="LA">LA</option>
		<option value="ME">ME</option>
		<option value="MD">MD</option>
		<option value="MA">MA</option>
		<option value="MI">MI</option>
		<option value="MN">MN</option>
		<option value="MS">MS</option>
		<option value="MO">MO</option>
		<option value="MT">MT</option>
		<option value="NE">NE</option>
		<option value="NV">NV</option>
		<option value="NH">NH</option>
		<option value="NJ">NJ</option>
		<option value="NM">NM</option>
		<option value="NY">NY</option>
		<option value="NC">NC</option>
		<option value="ND">ND</option>
		<option value="OH">OH</option>
		<option value="OK">OK</option>
		<option value="OR">OR</option>
		<option value="PA">PA</option>
		<option value="RI">RI</option>
		<option value="SC">SC</option>
		<option value="SD">SD</option>
		<option value="TN">TN</option>
		<option value="TX">TX</option>
		<option value="UT">UT</option>
		<option value="VT">VT</option>
		<option value="VA">VA</option>
		<option value="WA">WA</option>
		<option value="WV">WV</option>
		<option value="WI">WI</option>
		<option value="WY">WY</option>
	</select>
    <input type="text" name="z" value="${z}" placeholder="Zip Code"/>
    <input type="text" name="p" value="${p}" placeholder = "Phone Number"/><br><sup>State</sup><p>
   
    <input type="submit" value="Add" name="add">  <input type="submit" value="Cancel" name="add"> 
    </form>   
</div>
</div>
<div id="footer">
Copyright © Team One - CSC 481
</div>

</body>
</html>

