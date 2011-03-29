<%@ page language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%! String error = "false"; %>
<% 
	error = (String) request.getSession().getAttribute("error"); 
%>
<html>
	<head>
		<title>Home Automation System - Welcome</title>
		<link rel="stylesheet" type="text/css" href="css/mlogin.css" />
		<meta name="viewport" content="width=320" />
	</head>
	<body>
      <div class="mloginwrapper">
         <div class="mloginlogo">
         </div>
         <div class="mlogin">
			<div id="loginformcontainer">
				<% if ("true".equals(request.getParameter("error"))) { %>
					Invalid User ID or Password.
				<%} %>
				<form method="POST" action="../Login">
				<!-- action="http://YOUR_DOMAIN_HERE:[portnumber]/[locationofloginscript]" -->
			<form id="loginform">
               <p>
                  <img src="img/mloginname.png">
                  <input class="textfields" type="text" name="username" />
               </p>
               <p>
                  <img src="img/mloginpassword.png">
                  <input class="textfields" type="password" name="password" maxlength="20" />
               </p>
               <p class="submit">
                  <input class="submitbutton" type="image" src="img/mloginbutton.png" />
               </p>
</form>
				</form>
         </div>
      </div>
	</body>
</html>
