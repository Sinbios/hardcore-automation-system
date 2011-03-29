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
		
		<!--[if IE]>
            <link rel="stylesheet" type="text/css" href="css/ie.css" />
    <![endif]-->
    <!--[if !IE]><!-->
    	 <link rel="stylesheet" type="text/css" href="css/style.css" />
    <!--<![endif]-->
    
		<link rel="shortcut icon" href="images/has.ico" />
		
		<script src="javascript/jquery-1.5.1.min.js" type="text/javascript"></script>
		<script type="text/javascript" charset="utf-8">
            $(document).ready(function(){
                $('#submit').hover(
                    function(){ // On mouseover, swap the signout.png image
                        $(this).attr({ src : 'images/signout-hover.png'});
                    },
                    function(){ 
                        $(this).attr({ src : 'images/signout.png'});
                    }
                );
            });
        </script>
		
	</head>

	<body>
        <div id="wrapper">
        <%@ include file="header.jsp" %>
            <div id="maincontainer">
                <div id="status">
                    <p>To change your 4-digit PIN, enter your credentials:</p>
                    <% if ("oldpass".equals(request.getParameter("error"))) { %>
                    <p>Old pin was incorrect.</p>
                    <% } else if ("match".equals(request.getParameter("error"))) { %>
                    <p>New pin and verification do not match.</p>
                    <% } %>
                </div>
                <div class="pinfield">
                    <form action="SetComponentValue" method="POST" accept-charset="UTF-8"> 
                    	<input type="hidden" name="componentId" value="security"/>
                    	<input type="hidden" name="type" value="config"/>
                        <ul>
                            <li>
                                <label for="j_pin">Old Security PIN:</label>
                                <input class="pininput" type="password" name="oldpin" id="j_pin" tabindex="1" maxlength="4">
                            </li>

                            <li>
                                <label for="j_new_pin" >New Security PIN:</label>
                                <input class="pininput" type="password" name="newpin" id="j_new_pin" tabindex="2" maxlength="4">
                            </li>
                            
                            <li>
                                <label for="j_pin_confirm" >New Security PIN (confirm):</label>
                                <input class="pininput" type="password" name="verifypin" id="j_pin_confirm" tabindex="3" maxlength="4">
                            </li>

                            <li class="button">
                                <input type="submit" name="changepin" value="Submit" tabindex="4">
                            </li>
                        </ul>
                        
                    </form>
                </div>
            </div>
        </div>
        <%@ include file="footer.jsp" %>
    
	</body>
</html>

<% } %>