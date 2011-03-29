<%@ page language="java" %>
<%@ page import="com.hardcoresoft.has.security.UserSecurity" %>
<%@ page import="com.hardcoresoft.has.datastorage.UserPermission" %>
<%@ page import="com.hardcoresoft.has.datastorage.UserDataNode" %>
<%@ page import="com.hardcoresoft.has.datastorage.DataStorage" %>
<%@ page import="com.hardcoresoft.has.datastorage.HVACStatus" %>
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
<%! String currentTemp = String.valueOf(DataStorage.getInstance().getoHVACData().getoHVACData().getnCurrentTemperature()); %>
<%! String desiredTemp = String.valueOf(DataStorage.getInstance().getoHVACData().getoHVACData().getnDesiredTemperature()); %>
<%! HVACStatus status = DataStorage.getInstance().getoHVACData().getoHVACData().getoStatus(); %>
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
                	<%=status %>
                	<%=HVACStatus.ACCON %>
                	<%=HVACStatus.HEATON %>
                	<%=HVACStatus.FANON %>
                	<%=HVACStatus.OFF %>
                    <p>Current Temperature: <%=currentTemp %></p>
                    <p>Desired Temperature: <%=desiredTemp %></p>
                    <% if (status == HVACStatus.ACCON) { %>
                    	<p>Status: AC ON</p>
                    <% } else if (status == HVACStatus.HEATON) { %>
                    	<p>Status: Heat ON</p>
                    <% } else if (status == HVACStatus.FANON) { %>
                    	<p>Status: Fan ON</p>
                    <% } else if (status == HVACStatus.OFF) { %>
                    	<p>Status: OFF</p>
                   	<% } %>
                </div>
            
                <table>
                    <tr>
                        <td><a href="SetComponentValue?componentId=hvac&temp=off"><img src="images/turn-temp-off.png" /></a></td>
                        <td><a href="set-temp.jsp"><img src="images/set-temp.png" /></a></td>
                    </tr>
                    <tr>
                        <td><a href="temp-scheduling.jsp"><img src="images/schedule-temp.png" /></a></td>
                        <td><a href="SetComponentValue?componentId=hvac&fan=toggle"><img src="images/turn-fan-onoff.png" /></a></td>
                    </tr>
                </table>
            </div>
        </div>
    	<%@ include file="footer.jsp" %>
	</body>
</html>
<% } %>