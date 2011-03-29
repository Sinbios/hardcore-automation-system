<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.hardcoresoft.has.datastorage.DataStorage" %>
<%@ page import="com.hardcoresoft.has.datastorage.HVACStatus" %>
<p>Brightness: <%=DataStorage.getInstance().getoLightingData().getoLightingData().getnBrightness() %>%</p>
<p>Color: <%=Integer.toHexString(DataStorage.getInstance().getoLightingData().getoLightingData().getnColourTemp()).toUpperCase() %></p>
<% if (DataStorage.getInstance().getoLightingData().getoLightingData().getbOperationalStatus() == true) { %>
<p>Status: ON</p>
<% } else { %>
<p>Status: OFF</p>
<% } %>