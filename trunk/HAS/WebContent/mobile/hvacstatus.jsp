<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.hardcoresoft.has.datastorage.DataStorage" %>
<%@ page import="com.hardcoresoft.has.datastorage.HVACStatus" %>
<p>Current Temperature: <%=DataStorage.getInstance().getoHVACData().getoHVACData().getnCurrentTemperature() %></p>
<p>Desired Temperature: <%=DataStorage.getInstance().getoHVACData().getoHVACData().getnDesiredTemperature() %></p>
<% if (DataStorage.getInstance().getoHVACData().getoHVACData().getoStatus() == HVACStatus.ACCON) { %>
<p>Status: AC ON</p>
<% } else if (DataStorage.getInstance().getoHVACData().getoHVACData().getoStatus() == HVACStatus.HEATON) { %>
<p>Status: Heat ON</p>
<% } else if (DataStorage.getInstance().getoHVACData().getoHVACData().getoStatus() == HVACStatus.FANON) { %>
<p>Status: Fan ON</p>
<% } else if (DataStorage.getInstance().getoHVACData().getoHVACData().getoStatus() == HVACStatus.OFF) { %>
<p>Status: OFF</p>
<% } %>