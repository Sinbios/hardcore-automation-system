package com.hardcoresoft.has.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hardcoresoft.has.datastorage.DataStorage;
import com.hardcoresoft.has.datastorage.HVACStatus;
import com.hardcoresoft.has.messaging.HVACMessageSender;

/**
 * Servlet implementation class SetComponentValue
 */
@WebServlet("/SetComponentValue")
public class SetComponentValue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetComponentValue() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setComponentValue(request, response);
		response.sendRedirect("hvac.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setComponentValue(request, response);
		response.sendRedirect("hvac.jsp");
	}
	
	private static void setComponentValue(HttpServletRequest request, HttpServletResponse response) {
		String componentId = request.getParameter("componentId");
		if (componentId.equals("hvac")) {
			String desiredtemp = request.getParameter("desiredtemp");
			if (desiredtemp != null && !desiredtemp.equals(""))
				HVACMessageSender.getInstance().sendDesiredTemperature(Double.parseDouble(desiredtemp));
			String temp = request.getParameter("temp");
			if (temp != null && temp.equals("off"))
				HVACMessageSender.getInstance().sendStatus(HVACStatus.OFF);
			String fan = request.getParameter("fan");
			if (fan != null && fan.equals("toggle")) {
				if (DataStorage.getInstance().getoHVACData().getoHVACData().getoStatus() != HVACStatus.FANON)
					HVACMessageSender.getInstance().sendStatus(HVACStatus.FANON);
				else
					HVACMessageSender.getInstance().sendStatus(HVACStatus.OFF);
			}
		}
	}

}
