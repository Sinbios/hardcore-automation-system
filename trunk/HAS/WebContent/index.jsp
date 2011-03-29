<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">





<!-- YO GUYS, THIS IS JUST A REDIRECT SCRIPT DEPENDING ON IF YOU ARE USING A 
MOBILE BROWSER OR NOT. ITS NOT PERFECT, ITS NOT A SCIENCE, ITS JUST
THE MOST COMMON MOBILE BROWSERS AS OPPOSED TO NOT MOBILE BROWSERS

LAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

ALSO DONT KNOW IF THIS NEEDS TO BE JSP BUT IT IS	-->




<html>
	<head>
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
		console.log(uagent);
         if(mobile.detect()){
            window.location = "mobile/login.jsp";
         }else{
            window.location = "login.jsp";
         }

   </script>

	</head>

	<body>
	:3
	</body>
	</html>
