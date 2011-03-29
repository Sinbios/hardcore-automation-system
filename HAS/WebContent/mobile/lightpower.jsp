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
		<title>Home Automation System - Lights On!</title>
		<link rel="stylesheet" type="text/css" href="css/mstyle.css" />
		<meta name="viewport" content="width=320" />
	</head>
	<body>
      <div class="mwrapper">
                  <%@ include file="header.jsp" %>

         <div class="mcontent" style="text-align:right;">
         <!-- PLACE HOLDER, THATS IT -->
            <b> Master Bedroom </b>  [<a href="">ON</a>]<br>
            <b> Bathroom </b>  [<a href="">OFF</a>]<br>
            <b> Upstairs Hallway </b>  [<a href="">OFF</a>]<br>
            <b> Kitchen </b>  [<a href="">OFF</a>]<br>
            <b> Dining Room </b>  [<a href="">OFF</a>]<br>
            <b> Living Room </b>  [<a href="">OFF</a>]<br>
            <b> Downstairs Hallway </b>  [<a href="">OFF</a>]<br>
         </div>

         <%@ include file="footer.jsp" %>
      </div>
	</body>
</html>
<% } %>
