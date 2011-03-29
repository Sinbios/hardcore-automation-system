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
		<title>Home Automation System - Colour Temperature</title>
		<link rel="stylesheet" type="text/css" href="css/mstyle.css" />
		<meta name="viewport" content="width=320" />
	</head>
	<body>
      <div class="mwrapper">
         <%@ include file="header.jsp" %>
         <div class="mcontent">
         Current colour temperature is:<BR>
         <font color="#123dfe">#123dfe</font>
         <br><BR>
         Change colour temperature:<BR>
        <input class="temp" type="text" name="temperature" id="j_desired_temp" tabindex="1" maxlength="7" style="width:70px;height:20px;text-align:center;border:1px #009933 solid;">
         <li></li>
                                <input type="submit" name="settemp" value="Set" tabindex="2">
         </div>

         <%@ include file="footer.jsp" %>
      </div>
	</body>
</html>
<% } %>