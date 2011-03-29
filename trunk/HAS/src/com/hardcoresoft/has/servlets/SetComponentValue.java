package com.hardcoresoft.has.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hardcoresoft.has.datastorage.DataStorage;
import com.hardcoresoft.has.datastorage.HVACStatus;
import com.hardcoresoft.has.datastorage.SecurityMode;
import com.hardcoresoft.has.messaging.HVACMessageSender;
import com.hardcoresoft.has.messaging.LightingMessageSender;
import com.hardcoresoft.has.messaging.SecurityMessageSender;

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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setComponentValue(request, response);
		
	}
	
	private void setComponentValue(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String componentId = request.getParameter("componentId");
		if (componentId.equals("hvac")) {
			String desiredtemp = request.getParameter("desiredtemp");
			if (desiredtemp != null && !desiredtemp.equals("")) {
				HVACMessageSender.getInstance().sendDesiredTemperature(Double.parseDouble(desiredtemp));
			}
			String temp = request.getParameter("temp");
			if (temp != null && temp.equals("off")) {
				HVACMessageSender.getInstance().sendStatus(HVACStatus.OFF);
			}
			String fan = request.getParameter("fan");
			if (fan != null && fan.equals("toggle")) {
				if (DataStorage.getInstance().getoHVACData().getoHVACData().getoStatus() != HVACStatus.FANON) {
					HVACMessageSender.getInstance().sendStatus(HVACStatus.FANON);
				} else {
					HVACMessageSender.getInstance().sendStatus(HVACStatus.OFF);
				}
			}
			response.sendRedirect("hvac.jsp");
		} else if (componentId.equals("lighting")) {
			String status = request.getParameter("toggle");
			if (status != null && !status.equals("")) {
				if (DataStorage.getInstance().getoLightingData().getoLightingData().getbOperationalStatus() == false) {
					LightingMessageSender.getInstance().sendOperationalStatus(true);
				} else {
					LightingMessageSender.getInstance().sendOperationalStatus(false);
				}
			}
			String brightness = request.getParameter("brightness");
			if (brightness != null && !brightness.equals("")) {
				LightingMessageSender.getInstance().sendBrightness(Integer.valueOf(brightness));
			}
			String hex = request.getParameter("hex");
			if (hex != null && !hex.equals("")) {
				Integer color = Integer.parseInt(hex, 16);
				LightingMessageSender.getInstance().sendColourTemp(Integer.valueOf(color));
			}
			response.sendRedirect("lighting.jsp");
		} else if (componentId.equals("security")) {
			String pin = request.getParameter("pin");
			if (pin != null) {
				if (DataStorage.getInstance().getoSecurityData().getoSecurityData().getnPin() == Integer.valueOf(pin)) {
					if (DataStorage.getInstance().getoSecurityData().getoSecurityData().getoStatus() == SecurityMode.OFF)
						SecurityMessageSender.getInstance().sendStatus(SecurityMode.ARMED);
					else if (DataStorage.getInstance().getoSecurityData().getoSecurityData().getoStatus() == SecurityMode.ARMED)
						SecurityMessageSender.getInstance().sendStatus(SecurityMode.OFF);
					else if (DataStorage.getInstance().getoSecurityData().getoSecurityData().getoStatus() == SecurityMode.ALARMON)
						SecurityMessageSender.getInstance().disarmPin(Integer.valueOf(pin));
					response.sendRedirect("security.jsp");
				} else {
					response.sendRedirect("pin.jsp");
				}
			}
			String oldpin = request.getParameter("oldpin");
			String newpin = request.getParameter("newpin");
			String verifypin = request.getParameter("verifypin");
			if (oldpin != null && newpin != null && verifypin != null) {
				if (DataStorage.getInstance().getoSecurityData().getoSecurityData().getnPin() != Integer.valueOf(oldpin)) {
					response.sendRedirect("config.jsp?error=oldpass");
					return;
				}
				if (!newpin.equals(verifypin)) {
					response.sendRedirect("config.jsp?error=match");
					return;
				}
				DataStorage.getInstance().getoSecurityData().getoSecurityData().setnPin(Integer.valueOf(newpin));
				response.sendRedirect("security.jsp");
			}
		}
	}
}
