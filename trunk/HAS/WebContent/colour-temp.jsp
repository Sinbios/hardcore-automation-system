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
		<meta charset="utf-8">
		
		<link rel="shortcut icon" href="images/has.ico" />
		<link rel="stylesheet" href="javascript/themes/base/jquery.ui.all.css">
		
		<!--[if IE]>
            <link rel="stylesheet" type="text/css" href="css/ie.css" />
    <![endif]-->
    <!--[if !IE]><!-->
    	 <link rel="stylesheet" type="text/css" href="css/style.css" />
    <!--<![endif]-->
		
		<script src="javascript/jquery-1.5.1.js"></script>
		<script src="javascript/ui/jquery.ui.core.js"></script>
		<script src="javascript/ui/jquery.ui.widget.js"></script>
		<script src="javascript/ui/jquery.ui.mouse.js"></script>
		<script src="javascript/ui/jquery.ui.slider.js"></script>
		
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
        
	<style>
	#red, #green, #blue {
		float: left;
		clear: left;
		width: 300px;
		margin: 15px;
	}
	#swatch {
		width: 120px;
		height: 100px;
		margin-top: 18px;
		margin-left: 375px;
		background-image: none;
	}
	#red .ui-slider-range { background: #ef2929; }
	#red .ui-slider-handle { border-color: #ef2929; }
	#green .ui-slider-range { background: #8ae234; }
	#green .ui-slider-handle { border-color: #8ae234; }
	#blue .ui-slider-range { background: #729fcf; }
	#blue .ui-slider-handle { border-color: #729fcf; }
	#demo-frame > div.demo { padding: 10px !important; };
	</style>
	
	<% 
		String hex = Integer.toHexString(DataStorage.getInstance().getoLightingData().getoLightingData().getnColourTemp());
		hex = "000000" + hex;
		hex = hex.substring(hex.length() - 6, hex.length());
		Integer R = Integer.valueOf(hex.substring(0, 2), 16);
		Integer G = Integer.valueOf(hex.substring(2, 4), 16);
		Integer B = Integer.valueOf(hex.substring(4, 6), 16);
	%>
	<script>
	function hexFromRGB(r, g, b) {
		var hex = [
			r.toString( 16 ),
			g.toString( 16 ),
			b.toString( 16 )
		];
		$.each( hex, function( nr, val ) {
			if ( val.length === 1 ) {
				hex[ nr ] = "0" + val;
			}
		});
		return hex.join( "" ).toUpperCase();
	}
	function refreshSwatch() {
		var red = $( "#red" ).slider( "value" ),
			green = $( "#green" ).slider( "value" ),
			blue = $( "#blue" ).slider( "value" ),
			hex = hexFromRGB( red, green, blue );
		$("#hex").val(hex);
		$( "#swatch" ).css( "background-color", "#" + hex );
	}
	$(function() {
		$( "#red, #green, #blue" ).slider({
			orientation: "horizontal",
			range: "min",
			max: 255,
			value: 127,
			slide: refreshSwatch,
			change: refreshSwatch
		});
		$( "#red" ).slider( "value", <%=R %> );
		$( "#green" ).slider( "value", <%=G %> );
		$( "#blue" ).slider( "value", <%=B %> );
	});
	</script>        
        
	</head>

	<body>
        <div id="wrapper">
		<%@ include file="header.jsp" %>
            <div id="maincontainer">
				<div id="rgbwrapper">
					<div id="sliderdescription">
						Use the RGB sliders to change the colour temperature:
					</div>
					
					<div id="slidercontainer">
					
						<div id="red"></div>
						<div id="green"></div>
						<div id="blue"></div>

						<div id="swatch" class="ui-widget-content ui-corner-all"></div>
					</div>
					
					<div id="slidersubmitbutton">
						<form action="SetComponentValue" method="POST" accept-charset="UTF-8"> 
							<input type="hidden" name="componentId" value="lighting"/>
							<input type="hidden" id="hex" name="hex"/>
							<ul>
								<li class="button">
									<input type="submit" name="changecolourtemp" value="Set" tabindex="1">
									<input type="submit" name="clearcolourtemp" value="Clear" tabindex="2">
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