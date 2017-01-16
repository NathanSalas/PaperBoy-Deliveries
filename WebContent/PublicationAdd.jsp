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

=======
>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
<div id="bckgrnd">
<div id="section">
<h2>Add Publication</h2><br>
${Msg}<br>
<form action="PubAddSrvlt" method="post">
	
	<input type="text" name="title"/><br><sup>Title</sup><p>
	<input type="text" name="genre"/><br><sup>Genre</sup><p>
    <input type="text" name="price"/><br><sup>Price</sup><p>
    <select onchange="displayType()" name="freq" id="freq">
    	<option value="daily"> Daily </option>
    	<option value="weekly"> Weekly</option>
        <option value="monthly"> Monthly</option>
    </select>
    <fieldset name = "days" id="daycb" hidden=true>
    <legend> Delivery Days</legend>
		Sun<input type="radio" name="day" value="1"> | 
	    Mon<input type="radio" name="day" value="2"> |
	    Tue<input type="radio" name="day" value="3"> |
	    Wed<input type="radio" name="day" value="4"> |
	    Thu<input type="radio" name="day" value="5"> |
	    Fri<input type="radio" name="day" value="6"> |
	    Sat<input type="radio" name="day" value="7"> <p>
    </fieldset>
    <input type="text" name="mday" id="monthtb" placeholder="Start Date" hidden=true>
    <br>
    <input type="submit" value="Add">     
    </form> 
</div>
</div>	

<script type="text/javascript">
	window.displayType = function(){
		if(document.getElementById("freq").selectedIndex == "0"){
			document.getElementById('daycb').hidden = true;
			document.getElementById('monthtb').hidden = true;
		}else if(document.getElementById("freq").selectedIndex == "1"){
			document.getElementById('daycb').hidden = false;
			document.getElementById('monthtb').hidden = true;
		}else{
			document.getElementById('daycb').hidden = true;
			document.getElementById('monthtb').hidden = false;
		}
	}
</script>
<<<<<<< HEAD
</div>
=======
>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
<div id="footer">
Copyright © Team One - CSC 481
</div>

</body>
</html>

