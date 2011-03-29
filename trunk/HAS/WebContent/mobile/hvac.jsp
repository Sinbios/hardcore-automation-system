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
		<title>Home Automation System - HVAC</title>
		<link rel="stylesheet" type="text/css" href="css/mstyle.css" />
		<meta name="viewport" content="width=320" />
	</head>
	<body>
      <div class="mwrapper">
                 <%@ include file="header.jsp" %>
         <div class="mcontent">
            <div class="info">
               The temperature is on while the tempertaure is at lol degrees.
            </div>
            <div class="div1">
               <a href="temppower.jsp"><img src="img/mtemppower.png"></a></div>
            </div>
            <div class="div2">
               <a href="tempset.jsp"><img src="img/mtempset.png"></a>
            </div>
            <div class="div1">
               <a href="tempsched.jsp"><img src="img/mtempsched.png"></a>
            </div>
            <div class="div2">
               <a href="fanpower.jsp"><img src="img/mfanpower.png"></a>
            </div>
         </div>

        <%@ include file="footer.jsp" %>      
        </div>
	</body>
</html>
<% } %>