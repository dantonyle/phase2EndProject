<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>

<head>
<title>New Register Page</title>
</head>

<body>
	<form action="forgotPassword" method=post>
		<h1>Forgot Password</h1>
		<p>Please enter User-name and email below to retrieve password</p>
		<p>
			User-name: <input type="text" name="username" /> <br> <font
				color="red"> ${usernameERROR} </font>
		</p>
		<p>
			Email Account: <input type="text" name="email" /> <br> <font
				color="red"> ${emailERROR} </font>
		</p>
		<p>
			<font color="red"> ${error} </font>
		</p>
		<p>
			<input type="submit" />
		</p>
		<br>
	</form>
	 
	<form:form action="processButton" method="post">
	Return to: <input type = "submit" name = "welcomeBtn" value="Welcome Screen"/>
	</form:form>
</body>

</html>