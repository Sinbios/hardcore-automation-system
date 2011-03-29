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
		<title>Home Automation System - Account Settings</title>
		<link rel="stylesheet" type="text/css" href="css/mstyle.css" />
		<meta name="viewport" content="width=320" />
	</head>
	<body>
      <div class="mwrapper">
		<%@ include file="header.jsp" %>
         <div class="mcontent">
  		<div id="changepassform" class="pwdfield">
                    <div id="status">
                    <p>To change your password, enter your credentials:</p>
                    <% if ("oldpass".equals(request.getParameter("error"))) { %>
                    <p>Old password was incorrect.</p>
                    <% } else if ("match".equals(request.getParameter("error"))) { %>
                    <p>New password and verification do not match.</p>
                    <% } %>
                </div>			
                <div class="pwdfield">
                    <form action="../ChangePassword" method="POST" accept-charset="UTF-8"> 
                    
                        <ul>
                            <li>
                                <label for="j_password">Old password:</label>
                                <div class="fieldgroup">
                                    <input type="password" name="oldpassword" value="" id="j_password" tabindex="1" maxlength="20">
                                </div>
                            </li>

                            <li>
                                <label for="j_new_password" >New password:</label>
                                <div class="fieldgroup">
                                    <input type="password" name="newpassword" value="" id="j_new_password" tabindex="2" maxlength="20">
                                </div>
                            </li>
                            
                            <li>
                                <label for="j_password_confirm" >New password (confirm):</label>
                                <div class="fieldgroup">
                                    <input type="password" name="verification" value="" id="j_password_confirm" tabindex="3" maxlength="20">
                                </div>
                            </li>

                            <li class="button">
                                <input type="submit" name="changepass" value="Submit" tabindex="4">
                            </li>
                        </ul>
                        
                    </form>
                </div>

                </div>
            </div>
         </div>
         <%@ include file="footer.jsp" %>
      </div>
	</body>
</html>
<% } %>