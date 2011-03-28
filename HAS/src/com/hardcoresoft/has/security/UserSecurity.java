package com.hardcoresoft.has.security;

import javax.servlet.http.HttpServletRequest;

import com.hardcoresoft.has.datastorage.UserDataNode;
import com.hardcoresoft.has.datastorage.UserPermission;

public class UserSecurity {
	public static Boolean authenticationCheck(HttpServletRequest request) {
		UserDataNode user = (UserDataNode) request.getSession().getAttribute("user");
		if (user != null) {
			return true;
		}
		return false;
	}
	public static Boolean validateAccess(HttpServletRequest request, UserPermission permissionLevel) {
		UserDataNode user = (UserDataNode) request.getSession().getAttribute("user");
		if (user.getoUserPermission() == UserPermission.USER 
				&& permissionLevel == UserPermission.ADMIN) {
			return false;
		}
		return true;
	}
	
	/**
	 * Generic security check for pages. Takes in the level we deem the page to be at,
	 * returns true if the page is accessible, false if not.
	 * @param request
	 * @param permissionLevel
	 * @return
	 */
	public static Boolean pageSecurityCheck(HttpServletRequest request, UserPermission permissionLevel) {
		if (UserSecurity.authenticationCheck(request)) {
			if (!UserSecurity.validateAccess(request, permissionLevel)) {
				return false;
			}
			return true;
		}
		return false;
	}
}
