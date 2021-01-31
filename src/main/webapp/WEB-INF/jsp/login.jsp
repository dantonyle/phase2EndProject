<html>
<head>
<title>New Register Page</title>
</head>
<body>
	<form method=post>
		<h1>Login Form</h1>
		<div>
			<p>
				Username: <input type="text" name="username" /><br> <font
				color="red"> ${usernameERROR} </font>
			</p>
			<p>
				Password: <input type="text" name="password" /><br> <font
				color="red"> ${passwordERROR} </font>
			</p>
		</div>
		<div>
			<p>
				<input type="submit" /> <br>
			</p>
		</div>
		<div>
			<font color="red">${error}</font>
		</div>
	</form>
	<!-- form -->
</body>
</html>
