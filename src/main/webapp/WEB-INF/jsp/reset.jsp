<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>

<head>
<title>Reset Page</title>
</head>

<body>
	<form action="reset" method=post>
		<h1>Reset Password</h1>
		<p>Please enter User-name and Email and New Password below</p>
		<p>
			User-name: <input type="text" name="username" /> <br> <font
				color="red"> ${usernameERROR} </font>
		</p>
		<p>
			Email Account: <input type="text" name="email" /> <br> <font
				color="red"> ${emailERROR} </font>
		</p>
		<p>
			New Password: <input type="text" name="password" /> <br> <font
				color="red"> ${passwordERROR} </font>
		</p>
		<p>
			Re-confirm New Password: <input type="text" name="rePassword" /> <br> <font
				color="red"> ${rePasswordERROR} </font>
		</p>
		<p>
			<font color="red"> ${error} </font>
		</p>
		<p>
			<input type="submit" />
		</p>
		<br>
	</form>
	 
	<form:form action="resetWelcome" method="post">
	Return to: <input type = "submit" name = "welcomeBtn" value="Welcome Screen"/>
	</form:form>
</body>

</html>