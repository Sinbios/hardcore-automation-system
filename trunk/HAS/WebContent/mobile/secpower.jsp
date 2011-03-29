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
		<title>Home Automation System - Security Configuration</title>
		<link rel="stylesheet" toype="text/css" href="css/mstyle.css" />
		<meta name="viewport" content="width=320" />
		<script src="../../javascript/jquery-1.5.1.js"></script>
		 <script type="text/javascript">

		 </script>
         <style>
            #starting{
            font-size:30px;
            color:red;
            }
            #status{
            margin-bottom:20px;
            }
         </style>
	</head>
	<body>
      <div class="mwrapper">
<%@ include file="header.jsp" %>

         <div class="mcontent">
                <div id="status">
                    <p>To change your 4-digit PIN, enter your credentials:</p>
                </div>
            <div id="starting">
            </div>
                 <div id="changepassform" class="pinfield">
                                      <form action="../SetComponentValue" method="POST" accept-charset="UTF-8"> 
                    	<input type="hidden" name="componentId" value="security"/>
                        <ul>
                            <li>
                                <label for="j_pin">Security PIN:</label>
                                <input class="pininput" type="password" name="pin" id="j_pin" tabindex="1" maxlength="4">
                            </li>

                            <li class="button">
                                <input type="submit" name="togglesys" value="Submit" tabindex="2">
                            </li>
                        </ul>
                        
                    </form>
                </div>
            </div>
         </div>
<%@ include file="footer.jsp" %>

      </div>
	</body>
</html>
<% } %>