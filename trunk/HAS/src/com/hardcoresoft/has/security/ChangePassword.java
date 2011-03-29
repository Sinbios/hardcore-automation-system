package com.hardcoresoft.has.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hardcoresoft.has.datastorage.DataStorage;
import com.hardcoresoft.has.datastorage.UserDataNode;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		UserDataNode user = (UserDataNode) request.getSession().getAttribute("user");
		String oldpassword = request.getParameter("oldpassword");
		if (!user.getsPassword().equals(oldpassword)) {
			response.sendRedirect("settings.jsp?error=oldpass");
			return;
		}
		String newpassword = request.getParameter("newpassword");
		String verification = request.getParameter("verification");
		if (!newpassword.equals(verification)) {
			response.sendRedirect("settings.jsp?error=match");
			return;
		}
		user.changePassword(oldpassword, newpassword);
		DataStorage.getInstance().getoUserData().writeUserData();
		response.sendRedirect("settings.jsp?success=true");
	}

}
