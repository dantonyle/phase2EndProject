<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<title>Enter New Password Page</title>
</head>

<body>
	<form action="newPassword" method=post>
		<h1>New Password</h1>
		<p>Please enter New password for User: ${username}</p>
		<p>
			New Password: <input type="text" name="password" /> <br> <font
				color="red"> ${passwordERROR} </font>
		</p>
		<p>
			Re-confirm New Password: <input type="text" name="rePassword" /> <br> <font
				color="red"> ${repasswordERROR} </font>
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