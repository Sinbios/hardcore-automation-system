<%@ page language="java" %>
<%@ page import="com.hardcoresoft.has.security.UserSecurity" %>
<%@ page import="com.hardcoresoft.has.datastorage.UserPermission" %>
<%@ page import="com.hardcoresoft.has.datastorage.UserDataNode" %>
<%@ page import="com.hardcoresoft.has.datastorage.DataStorage" %>
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
<% Boolean hvacConnected = DataStorage.getInstance().getoHVACData().getoHVACData().getbConnected(); %>
<% Boolean lightingConnected = DataStorage.getInstance().getoLightingData().getoLightingData().getbConnected(); %>
<% Boolean securityConnected = DataStorage.getInstance().getoSecurityData().getoSecurityData().getbConnected(); %>
<% String hvacSrc = "";%>
<% String liteSrc = "";%>
<% String secSrc = "";%>
<% String hvacHref = "";%>
<% String liteHref = "";%>
<% String secHref = "";%>
<% if ( hvacConnected )
	{
		hvacSrc = "images/hvac.png";
		hvacHref = "hvac.jsp";
	}
	else
	{
		hvacSrc = "images/hvac-na.png";
		hvacHref = "";
	}
%>
<% if ( lightingConnected )
	{
		liteSrc = "images/lighting.png";
		liteHref = "lighting.jsp";
	}
	else
	{
		liteSrc = "images/lighting-na.png";
		liteHref = "";
	}
%>
<% if ( securityConnected )
	{
		secSrc = "images/security.png";
		secHref = "security.jsp";
	}
	else
	{
		secSrc = "images/security-na.png";
		secHref = "";
	}
%>
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
                        <td><a href=<%=hvacHref%>><img src=<%=hvacSrc%> /></a></td>
                        <td><a href=<%=secHref%>><img src=<%=secSrc%> /></a></td>
                    </tr>
                    <tr>
                        <td><a href=<%=liteHref%>><img src=<%=liteSrc%> /></a></td>
                        <td></td>
                    </tr>
                </table>
            </div>
        </div>
    	<%@ include file="footer.jsp" %>
	</body>
</html>
<% } %>