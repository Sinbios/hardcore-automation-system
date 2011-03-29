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
		<title>Home Automation System - Lighting</title>
		<link rel="stylesheet" type="text/css" href="css/mstyle.css" />
		<meta name="viewport" content="width=320" />
	</head>
	<body>
      <div class="mwrapper">

                 <%@ include file="header.jsp" %>

         <div class="mcontent">
            <div class="div1">
               <a href="lightpower.jsp"><img src="img/mlightpower.png"></a>
            </div>
            <div class="div2">
               <a href="lightadjust.jsp"><img src="img/mlightadjust.png"></a>
            </div>
            <div class="div1">
               <a href="lightsched.jsp"><img src="img/mlightsched.png"></a>
            </div>
            <div class="div2">
               <a href="lighttemp.jsp"><img src="img/mlighttemp.png"></a>
            </div>
         </div>
                 <%@ include file="footer.jsp" %>

      </div>
	</body>
</html>
<% } %>