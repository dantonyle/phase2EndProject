<html>

<head>
<title>New Register Page</title>
</head>

<body>
	<form method=post>
		<h1>Welcome New User!!</h1>
		<p>Please enter new account details below</p>
		<p>User-name: <input type="text" name="name" /> <br> <font color="red"> ${usernameERROR} </font> </p>
		<p>Password: <input type="text" name="password" /> <br> <font color="red">  ${passwordERROR} </font> </p>
		<p>Email Account: <input type="text" name="email" /> <br> <font color="red">  ${emailERROR} </font> </p>
		<p><input type="submit" />  </p>
	</form>
</body>

</html>