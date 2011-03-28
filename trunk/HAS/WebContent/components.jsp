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
<%! String selected = "components"; %>
<% user = (UserDataNode) request.getSession().getAttribute("user"); %>
<html>
	<head>
		<title>Home Automation System - My Components</title>
		
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
                <table id="simpletable">
                    <tr>
                        <td><a href="hvac.html"><img src="images/hvac.png" /></a></td>
                        <td><a href="security.html"><img src="images/security.png" /></a></td>
                    </tr>
                    <tr>
                        <td><a href="lighting.html"><img src="images/lighting.png" /></a></td>
                        <td></td>
                    </tr>
                </table>
            </div>
        </div>
    	<%@ include file="footer.jsp" %>
	</body>
</html>
<% } %>