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
		<link rel="stylesheet" type="text/css" href="css/mstyle.css" />
		<meta name="viewport" content="width=320" />
		
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
                $.get('securitystatus.jsp', function(data) {
               		$('#status').html(data);
               	});
                $(document).everyTime(1000, 'controlled', function() {
                	$.get('securitystatus.jsp', function(data) {
                   		$('#status').html(data);
                   	});
				});
            });
        </script>
	</head>
	<body>
      <div class="mwrapper">
      
         <%@ include file="header.jsp" %>
         <div class="mcontent">
            <div class="status">
            </div>
            <a href="secpower.jsp"><img src="img/msecuritypower.png"></a>
            <br>
            <a href="secconfig.jsp"><img src="img/msecurityconfig.png"></a>
         </div>
       <%@ include file="footer.jsp" %>
      </div>
	</body>
</html>
<% } %>