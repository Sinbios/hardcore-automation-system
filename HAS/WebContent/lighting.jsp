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
		<!--[if IE]>
            <link rel="stylesheet" type="text/css" href="css/ie.css" />
    	<![endif]-->
    	<!--[if !IE]><!-->
			<link rel="stylesheet" type="text/css" href="css/style.css" />
		<!--<![endif]-->
		<link rel="shortcut icon" href="images/has.ico" />
		<script src="javascript/jquery-1.5.1.min.js" type="text/javascript"></script>
		<script src="javascript/jquery.timers.js" type="text/javascript"></script>
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
                $.get('lightingstatus.jsp', function(data) {
               		$('#status').html(data);
               	});
                $(document).everyTime(1000, 'controlled', function() {
                	$.get('lightingstatus.jsp', function(data) {
                   		$('#status').html(data);
                   	});
				});
            });
        </script>
		
	</head>

	<body>
        <div id="wrapper">
            <%@ include file="header.jsp" %>
            <div id="maincontainer">
                <div id="status">
                    
                </div>
            
                <table>
                    <tr>
                        <td><a href="SetComponentValue?componentId=lighting&toggle=true"><img src="images/lights-onoff.png" /></a></td>
                        <td><a href="adjust-brightness.jsp"><img src="images/adjust-brightness.png" /></a></td>
                    </tr>
                    <tr>
                        <td><a href="lighting-scheduling.jsp"><img src="images/lighting-schedule.png" /></a></td>
                        <td><a href="colour-temp.jsp"><img src="images/colour-temperature.png" /></a></td>
                    </tr>
                </table>
            </div>
        </div>
        <%@ include file="footer.jsp" %>
	</body>
</html>
<% } %>