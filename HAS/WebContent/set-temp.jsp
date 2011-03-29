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
                    <p>Status information regarding the component goes here</p>
                </div>
            				
                <div id="changetempform" class="tempfield">
                    <form action="SetComponentValue" method="POST" accept-charset="UTF-8"> 
                    	<input type="hidden" name="componentId" value="hvac"/>
                        <ul>
                            <li>
                                <label for="j_desired_temp">Enter the desired temperature (in degrees celsius):</label>
                                <input class="degreefield" type="text" name="desiredtemp" id="j_desired_temp" tabindex="1" maxlength="2">
                            </li>

                            <li class="button">
                                <input type="submit" name="settemp" value="Set" tabindex="2">
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