<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<title>Welcome</title>
</head>
<body>
	<h1>Welcome to Phase 2 End Project</h1><br>
	<p><h1><font color="green">${name}</font></h1></p>
		<br>
	<form:form action="processButton" method="post">
     <input type = "submit" name = "loginBtn" value="Login"/>
     <input type = "submit" name = "registerBtn" value="Register"/>
     <input type = "submit" name = "forgotBtn" value="Forgot Password"/> 
   </form:form>
   <br>
   <p><h1><font color="blue">${message}</font></h1></p>
   <br>
   <h4>By Dan Tony Le</h4>
   </body>
</html>
