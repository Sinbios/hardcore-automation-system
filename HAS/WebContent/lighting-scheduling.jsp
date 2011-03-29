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
		<meta charset="utf-8">
		
	   <!--[if IE]>
          <link rel="stylesheet" type="text/css" href="css/ie.css" />
      <![endif]-->
      <!--[if !IE]><!-->
          <link rel="stylesheet" type="text/css" href="css/style.css" />
      <!--<![endif]-->
		
		<link rel="shortcut icon" href="images/has.ico" />
		<link rel="stylesheet" href="javascript/themes/base/jquery.ui.all.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.11/jquery-ui.min.js"></script>
		<script src="javascript/jquery-ui-timepicker-addon.js"></script>
		<script src="javascript/ui/jquery.ui.core.js"></script>
		<script src="javascript/ui/jquery.ui.widget.js"></script>
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
		
		<script type="text/javascript" charset="utf-8">
         $(document).ready(function() {
	         $('.tabs a').click(function(){
		         switch_tabs($(this));
	         });
	         switch_tabs($('.defaulttab'));
         });
          
         function switch_tabs(obj)
         {
	         $('.tab-content').hide();
	         $('.tabs a').removeClass("selected");
	         var id = obj.attr("rel");
          
	         $('#'+id).show();
	         obj.addClass("selected");
         }
      </script>       
		<script>
		   $(function() {
			   $( "#slider" ).slider({
				   range: "min",
				   min: 10,
				   max: 40,
				   orientation: "vertical",
				   value: 22,
				   slide: function( event, ui ) {
					   $( "#amount" ).val( ui.value );
				   }
			   });
			   $( "#amount" ).val( $( "#slider" ).slider( "value" ) );
		   });
		</script>
		
      <script type="text/javascript">
         $(document).ready(function() {
            var currentTime = new Date();
$('#startdate').datetimepicker({
    ampm: true,
    minDate: new Date(
        currentTime.getFullYear(), 
        currentTime.getMonth() + 1, 
        currentTime.getDate(), 
        currentTime.getHours(), 
        currentTime.getMinutes()
    ),
    onSelect: function( dateText ) {
        $( '#starting' ).text( dateText) ;
    }
});
            $('#enddate').datetimepicker({ampm: true, minDate: new Date(currentTime.getFullYear(), currentTime.getMonth() + 1, currentTime.getDate(), currentTime.getHours(), currentTime.getMinutes())});
            
         });
      </script>
		<style type="text/css"> 
         /* css for timepicker */
         .ui-timepicker-div .ui-widget-header{ margin-bottom: 8px; }
         .ui-timepicker-div dl{ text-align: left; }
         .ui-timepicker-div dl dt{ height: 25px; }
         .ui-timepicker-div dl dd{ margin: -25px 0 10px 65px; }
         .ui-timepicker-div td { font-size: 90%; }
         #ui-datepicker-div {font-size:12px;}
         
			.ui-slider .ui-slider-handle {height:10px;width:13px;}
         ul.tabs {
             margin:0;
             padding:0;
         }
         ul.tabs li {
             display:block;
             float:left;
             padding:0 5px;
         }
         ul.tabs li a {
             display:block;
             float:left;
             padding:5px;
             font-size:0.8em;
             background-color:#e0e0e0;
             color:#666;
             text-decoration:none;
         }
         .selected {
             font-weight:bold;
         }
         .tab-content {
             clear:both;
             border:1px solid #ddd;
         }
		 .tableheadings {
			padding-right: 15px;
		 }
         #tabwrap
         {
            width: 650px;
            height: 400px;
         }
         #maincontainer td
         {
            height:15px;
            text-align:left;
			padding-right: 15px;
         }
         table{
            width: 99%;
         }
         #ui-datepicker-div{
            border: none;
         }
         .div1{
            float:left; width:75%;height:300px;overflow:hidden;
         } 
         .div2{
            margin-left:75%; width:25%; height:300px; overflow:hidden;
         } 
         #schedule{
            font-size:12px;
         }
		</style> 
	</head>

	<body>
      <div id="wrapper">
         <%@ include file="header.jsp" %>
         <div id="maincontainer">
             <div id="status">
                 <p>Status information regarding the component goes here</p>
             </div>
             
             <div id="tabwrap">
                <ul class="tabs">
                    <li><a href="#" class="defaulttab" rel="tabs1">View Schedule</a></li>
                    <li><a href="#" rel="tabs2">Add Schedule</a></li>
                    <li><a href="#" rel="tabs3">Edit Schedule</a></li>
                </ul>
             
                <div class="tab-content" id="tabs1">
                <!-- this is where we grab the schedule data from server -->
                  <table>
                     <tr>
                        <td class="tableheadings">
                           <b>Start Date</b>
                        </td>
                        <td class="tableheadings">
                           <b>End Date</b>
                        </td>
                        <td class="tableheadings">
                           <b>State</b>
                        </td>
                        <td class="tableheadings">
                           <b>Brightness</b>
                        </td>
                        <td class="tableheadings">
                           <b>Colour Temp.</b>
                        </td>
                     </tr>
                     <tr>
                        <td>
                           January 1st 4:00 AM
                        </td>
                        <td>
                           January 1st 3:50 PM
                        </td>
                        <td>
                           ON
                        </td>
                        <td>
                           65%
                        </td>
                        <td>
                           #009933
                        </td>
                     </tr>
                     <tr>
                        <td>
                           January 1st 3:50 PM
                        </td>
                        <td>
                           January 1st 10:00 PM
                        </td>
                        <td>
                           OFF
                        </td>
                        <td>
                           0%
                        </td>
                        <td>
                           #000000
                        </td>
                     </tr>   
                  </table> 
                </div>
                <div class="tab-content" id="tabs2">
                <!-- this is where we let people go yo, less make some scheds -->
               scheduling functionality in v1.1
               </div>

             <div class="tab-content" id="tabs3">
               edit schedule functionality in v1.2
             </div>
          </div>      
      </div>
   
      <%@ include file="footer.jsp" %>
	</body>
</html>
<% } %>