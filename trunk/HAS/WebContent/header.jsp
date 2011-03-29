<div id="headerwrapper">
    <div id="header">
        <img src="images/has-logo-main.png" />
    </div>
    
    <div id="topnavwrapper">
        <div id="topleftnav">
            <p><a href="home.jsp">Home</a> > <a href="components.jsp">My Components</a></p>
        </div>

        <div id="welcomebar">
            <p>Welcome, <%=user.getsFirstName() %>!</p>
        </div>
    </div>
</div>

<div id="navcont">
    <div id="menu">
        <ul>
            <li><a <%if ("home".equals(selected)) { %>id="homeactive"<% } %> class="home" href="home.jsp">Home</a></li>
            <li><a <%if ("components".equals(selected)) { %>id="mycomponentsactive"<% } %> class="mycomponents" href="components.jsp">My Components</a></li>
            <li><a <%if ("settings".equals(selected)) { %>id="acctsettingsactive"<% } %> class="acctsettings" href="settings.jsp">Account Settings</a></li>
        </ul>
        <form method="post" action="Logout" name="logout">
            <input id="submit" class="submitbutton" type="image" src="images/signout.png">
            <input type="hidden" name="logoutExitPage" value="login.jsp">
        </form>
    </div>
</div>