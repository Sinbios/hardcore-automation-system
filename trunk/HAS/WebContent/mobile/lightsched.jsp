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
		<title>Home Automation System - Light Schedule</title>
		<link rel="stylesheet" type="text/css" href="css/mstyle.css" />
		<meta name="viewport" content="width=320" />
	</head>
	<body>
      <div class="mwrapper">
         <%@ include file="header.jsp" %>
         <div class="mcontent">
         room 1
            <table style="margin:0 auto;width:220px;">
               <tr>
                  <td>
                     Start
                  </td>
                  <td>
                     End
                  </td>
                  <td>
                     Brightness
                  </td>
                  <td>
                     Colour Temp
                  </td>                  
               </tr>
               <tr>
                  <td>
                     oct 6
                  </td>
                  <td>
                     oct 10
                  </td>
                  <td>
                     100%
                  </td>
                  <td>
                     <font color="#aabbcc">#aabbcc</font>
                  </td>
               </tr>
               <tr>
                  <td>
                     oct 10
                  </td>
                  <td>
                     oct 13
                  </td>
                  <td>
                     100%
                  </td>
                  <td>
                     <font color="#dd0033">#dd0033</font>
                  </td>
               </tr>
            </table>
         </div>

         <%@ include file="footer.jsp" %>

      </div>
	</body>
</html>
<% } %>