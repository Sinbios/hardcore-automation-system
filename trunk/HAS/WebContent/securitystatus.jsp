<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.hardcoresoft.has.datastorage.DataStorage" %>
<%@ page import="com.hardcoresoft.has.datastorage.SecurityMode" %>
<% if (DataStorage.getInstance().getoSecurityData().getoSecurityData().getoStatus() == SecurityMode.ALARMON) { %>
<p>Status: ALARM</p>
<% } else if (DataStorage.getInstance().getoSecurityData().getoSecurityData().getoStatus() == SecurityMode.ARMED) { %>
<p>Status: ARMED</p>
<% } else { %>
<p>Status: OFF</p>
<% } %>