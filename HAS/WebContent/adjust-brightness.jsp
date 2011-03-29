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
		
		<meta charset="utf-8">
		<link rel="stylesheet" href="javascript/themes/base/jquery.ui.all.css">
		<script src="javascript/jquery-1.5.1.min.js"></script>
		<script src="javascript/ui/jquery.ui.core.js"></script>
		<script src="javascript/ui/jquery.ui.widget.js"></script>

		<script src="javascript/ui/jquery.ui.mouse.js"></script>
		<script src="javascript/ui/jquery.ui.slider.js"></script>
		
		<script type="text/javascript" charset="utf-8">
            $(document).ready(function(){
            $("#amount").text( "0%");
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
		<script>
		$(function() {
			$( "#slider" ).slider({
				range: "min",
				min: 0,
				max: 100,
				value: <%=DataStorage.getInstance().getoLightingData().getoLightingData().getnBrightness() %>,
				slide: function( event, ui ) {
					$("#amount" ).val( ui.value );
					$("#amount").text( ui.value+"%" );
					$("#brightness").val( ui.value );
				}
			});
			$("#amount" ).val( <%=DataStorage.getInstance().getoLightingData().getoLightingData().getnBrightness() %> );
			$("#amount").text( <%=DataStorage.getInstance().getoLightingData().getoLightingData().getnBrightness() %>+"%" );
			$("#brightness").val( <%=DataStorage.getInstance().getoLightingData().getoLightingData().getnBrightness() %> );
		});
		</script>
		<style>	
         #slider{width:250px; position:absolute; left:55px; top:65px;}
         #brightnesscontainer{position:relative;height:150px;}
         #amount{position:absolute; right:12px; top:26px;}
         #slidersubmitbutton input{position:absolute;bottom:20px;right:20px;}
		</style>
	</head>

	<body>
        <div id="wrapper">
        <%@ include file="header.jsp" %>
            <div id="maincontainer">
                <div class="brightnessfield">
					    <div id="brightnesscontainer">
						<label for="amount">Desired Brightness Level:</label>
                  		<div id="amount"></div>
						<div id="slider"></div>
						<div id="slidersubmitbutton">
							<form action="SetComponentValue" method="POST" accept-charset="UTF-8">
								<input type="hidden" name="componentId" value="lighting"/>
								<input type="hidden" id="brightness" name="brightness"/>
								<ul>
									<li class="button">
										<input type="submit" name="changebrightness" value="Set" tabindex="1">
									</li>
								</ul>
							</form>
						</div>
		            </div>
        	</div>
        </div>
        <%@ include file="footer.jsp" %>
	</body>
</html>
<% } %>