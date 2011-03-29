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
<%! String selected = "home"; %>
<% user = (UserDataNode) request.getSession().getAttribute("user"); %>


<script type="text/javascript">

         var mobile = function(){
	         return {
		         detect:function(){
			         var uagent = navigator.userAgent.toLowerCase(); 
			         var list = this.mobiles;
			         var ismobile = false;
			         for(var d=0;d<list.length;d+=1){
				         if(uagent.indexOf(list[d])!=-1){
					         ismobile = true;
				         }
			         }
			         return ismobile;
		         },
		         mobiles:[
			         "midp","240x320","blackberry","netfront","nokia","panasonic",
			         "portalmmm","sharp","sie-","sonyericsson","symbian",
			         "windows ce","benq","mda","mot-","opera mini",
			         "philips","pocket pc","sagem","samsung","sda",
			         "sgh-","vodafone","xda","palm","iphone",
			         "ipod","android"
		         ]
	         };
         }();

         if(mobile.detect()){
            window.location = "mobile/home.jsp";
         }

   </script>

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
            <%@ include file="header.jsp" %>

            <div id="maincontainer">
                <div id="homemessage">
                    <p>Welcome to your Home Automation System!</p>
                    <p>Please visit the "My Components" page to begin configuring properties of the various components in your home.</p>
                    <p>Click "Account Settings" to perform tasks such as changing your HAS password.</p>
                </div>
            </div>
        
        </div>
        <%@ include file="footer.jsp" %>
	</body>
</html>
<% } %>