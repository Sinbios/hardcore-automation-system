package com.hardcoresoft.has.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hardcoresoft.has.datastorage.DataStorage;
import com.hardcoresoft.has.datastorage.UserDataNode;

/**
 * Servlet implementation class Login
 */
@WebServlet(description = "login process", urlPatterns = { "/Login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter (); 
		String username = request.getParameter ("username");
		String password = request.getParameter ("password");
		String user = request.getParameter ("username");
		HttpSession session = request.getSession(true); 
		session.setAttribute("user", username);
		
		UserDataNode userNode = DataStorage.getInstance().getoUserData().getoUserData().findUser(username);
		
		if (userNode != null) {
			if (userNode.validatePassword(password)) {
				session.setAttribute("user", user); 
				session.setAttribute("username", username); 
				// TODO: this needs to redirect to a JSP file that checks session for user info
				response.sendRedirect("index.html");
			} else {
				// TODO: redirect back to login page with username filled in
				out.println ("<h3>Invalid username/password.</h3>");
			}
		} else {
			// TODO: redirect back to login page with username filled in
			out.println ("<h3>Invalid username/password.</h3>");
		}
	}
}
