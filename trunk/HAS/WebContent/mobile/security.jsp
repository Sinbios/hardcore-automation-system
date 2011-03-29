<%@ page language="java" %>
<%@ page import="com.hardcoresoft.has.security.UserSecurity" %>
<%@ page import="com.hardcoresoft.has.datastorage.UserPermission" %>
<%@ page import="com.hardcoresoft.has.datastorage.UserDataNode" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<% 
	boolean isLoggedIn = UserSecurity.authenticationCheck(request);
	if (!isLoggedIn) {
%>
<%@ include file="login.jsp" %>
<% } else { %>
<%! UserDataNode user = null; %>
<%! String selected = "components"; %>
<% user = (UserDataNode) request.getSession().getAttribute("user"); %>
<html>
	<head>
		<title>Home Automation System - Security</title>
		<link rel="stylesheet" type="text/css" href="css/mstyle.css" />
		<meta name="viewport" content="width=320" />
	</head>
	<body>
      <div class="mwrapper">
      
         <%@ include file="header.jsp" %>
         <div class="mcontent">
            <div class="info">
               The security system is probably on lol.
            </div>
            <a href="secpower.jsp"><img src="img/msecuritypower.png"></a>
            <br>
            <a href="secconfig.jsp"><img src="img/msecurityconfig.png"></a>
         </div>
       <%@ include file="footer.jsp" %>
      </div>
	</body>
</html>
<% } %>