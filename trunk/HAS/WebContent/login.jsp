<%@ page language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%! String error = "false"; %>
<% 
	error = (String) request.getSession().getAttribute("error"); 
%>
<html>
	<head>
		<title>Home Automation System - Login</title>

		<!--[if IE]>
            <link rel="stylesheet" type="text/css" href="css/ielogin.css" />
		<![endif]-->
		<!--[if !IE]><!-->
			 <link rel="stylesheet" type="text/css" href="css/login.css" />
		<!--<![endif]-->
       
		<link rel="shortcut icon" href="images/has.ico" />
	</head>
	
	<body>
		<div id="loginwrapper">
			<div id="loginlogocontainer"><img src="images/has-logo.png" /></div>
			<div id="loginformcontainer">
				<% if ("true".equals(request.getParameter("error"))) { %>
					Invalid User ID or Password.
				<%} %>
				<form method="POST" action="Login">
				<!-- action="http://YOUR_DOMAIN_HERE:[portnumber]/[locationofloginscript]" -->
					<form id="loginform">
						<ul>
							<li><img class="useridimg" src="images/userid.png" /><input class="textfields" type="text" name="username" /></li>
							<li><img class="passwdimg" src="images/password.png" /><input class="textfields" type="password" name="password" maxlength="20" /></li>
							<li><input class="submitbutton" type="image" src="images/loginbutton.png" /></li>
						</ul>
					</form>
				</form>
			</div>
		</div>
	</body>
</html>