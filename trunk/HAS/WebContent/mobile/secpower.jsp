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


         function securitysystem(userpin)
         {
            var system = 1;
            
            if (system == 1)
            {
               system = 0;
               $("#starting").text("deactivated");
            }
            else
            {
               system = 1;
               $("#starting").text("actived");
            }
         }


         function validate(pin)
         {
         var system = "active";
         var pininthesystem = "111";
         if (pin==pininthesystem)
          {
             if (system=="active")
             {
               $('#starting').text('Security system deactivated');
             }
             else
             {
               $('#starting').text('Security system activated');
             }
             setTimeout ( "redirectlol()", 7500 );
          }
          else
          {
            $('#starting').text('Incorrect PIN');
            setTimeout ( "fadeawaylol()", 2500 ); 
          }
         }
         function fadeawaylol()
         { 
         $('#starting').text('');
         }
         function redirectlol()
         {
            location.href='../security.html';
         }
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
                    <form name="pin">
                       Enter your pin:<br>
                       <input class="pininput" type="password" name="userpin" id="j_pin" tabindex="1" maxlength="4" style="width:50px;height:20px;text-align:center;border:1px #009933 solid;">
                      <input type="submit" value="Change" style="margin-top:10px;" tabindex="2" onclick=javascript: systemsecurity(userpin.value)>
                    </form>
                </div>
            </div>
         </div>
<%@ include file="footer.jsp" %>

      </div>
	</body>
</html>
<% } %>