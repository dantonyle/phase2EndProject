<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<title>Welcome</title>
</head>
<body>
	<h1>Welcome to Phase 2 End Project</h1>
		<br>
	<form:form action="processButton" method="post">
     <input type = "submit" name = "loginBtn" value="Login"/>
     <input type = "submit" name = "registerBtn" value="Register"/> 
   </form:form>
   <br>
   <p><font color="blue"><h2>${message}</h2></font></p>
   <br>
   <h4>By Dan Tony Le</h4>
   </body>
</html>
