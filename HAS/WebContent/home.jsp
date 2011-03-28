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
<%@ include file="login.html" %>
<% } else { %>
<%! UserDataNode user = null; %>
<% user = (UserDataNode) request.getSession().getAttribute("user"); %>
<html>
	<head>
		<title>Home Automation System - Welcome</title>
		
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
            <div id="headerwrapper">
                <div id="header">
                    <img src="images/has-logo-main.png" />
                </div>
                
                <div id="topnavwrapper">
       
                    <div id="welcomebar">
                        <p>Welcome, <%=user.getsFirstName() %>!</p>
                    </div>
                </div>
            </div>
            
            <div id="navcont">
                <div id="menu">
                    <ul>
                        <li><a id="homeactive" class="home" href="home.html">Home</a></li>
                        <li><a class="mycomponents" href="components.html">My Components</a></li>
                        <li><a class="acctsettings" href="settings.html">Account Settings</a></li>
                    </ul>
                    <form method="post" action="Logout" name="logout">
                        <input id="submit" class="submitbutton" type="image" src="images/signout.png">
                        <input type="hidden" name="logoutExitPage" value="login.html">
                    </form>
                </div>
            </div>

            <div id="maincontainer">
                <div id="homemessage">
                    <p>Welcome to your Home Automation System!</p>
                    <p>Please visit the "My Components" page to begin configuring properties of the various components in your home.</p>
                    <p>Click "Account Settings" to perform tasks such as changing your HAS password.</p>
                </div>
            </div>
        
        </div>
        
        <div id="footer">
            <p>Copyright &copy;2011 | Home Automation System&#0153; by Hardcoresoft | All Rights Reserved</p>
        </div>
    
	</body>
</html>
<% } %>