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
		<title>Home Automation System - Brightness</title>
		<link rel="stylesheet" type="text/css" href="css/mstyle.css" />
		<meta name="viewport" content="width=320" />
	</head>
	<body>
      <div class="mwrapper">
         <%@ include file="header.jsp" %>
         <div class="mcontent">
         Current brightness is:<BR>
         13%
         <br><BR>
         Change brightness:<BR>
         
         <input class="bright" type="text" name="brightness" id="j_desired_bright" tabindex="1" maxlength="3" style="width:50px;height:20px;text-align:center;border:1px #009933 solid;">
         <input type="submit" name="setbright" value="Set" tabindex="2">
         </div>

         <%@ include file="footer.jsp" %>
      </div>
	</body>
</html>

<% } %>