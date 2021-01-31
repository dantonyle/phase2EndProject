
<html>

<head>
<title>New Register Page</title>
</head>

<body>
	<form method=post>
		<h1>Welcome New User!!</h1>
		<p>Please enter new account details below</p>
		<p>
			User-name: <input type="text" name="username" /> <br> <font
				color="red"> ${usernameERROR} </font>
		</p>
		<p>
			Password: <input type="text" name="password" /> <br> <font
				color="red"> ${passwordERROR} </font>
		</p>
		<p>
			Re-confirm Password: <input type="text" name="repassword" /> <br> <font
				color="red"> ${repasswordERROR} </font>
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
</body>

</html>