package com.softserve.edu.dashboard.controllers.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.edu.dashboard.constants.Attributes;
import com.softserve.edu.dashboard.constants.Messages;
import com.softserve.edu.dashboard.constants.WebPaths;
import com.softserve.edu.dashboard.dto.UserDTO;
import com.softserve.edu.dashboard.tools.Context;
import com.softserve.edu.dashboard.tools.UserUtils;

@WebServlet(WebPaths.USER_CREATE_SERVLET)
public class UserProfileCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserProfileCreateServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet from UserProfileCreateServlet");
		UserDTO userDTO = (UserDTO) request.getSession().getAttribute(Attributes.USER_DTO);
		if (userDTO != null) {
			request.getRequestDispatcher(WebPaths.USER_ITEMS_SERVLET).forward(request, response);
		} else {
			request.getRequestDispatcher(WebPaths.USER_PROFILE_JSP).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost from UserProfileCreateServlet");
		UserDTO userDTO = (UserDTO) request.getSession().getAttribute(Attributes.USER_DTO);
		UserDTO userDTO2 = UserUtils.initUserDTO(request, userDTO);
		System.out.println("userDTO2:" + userDTO2);
		if (userDTO2 != null) {
			if (userDTO != null) {
				Context.getInstance().getUserProfileService().setUserDTO(userDTO2);
				UserUtils.createSession(request, userDTO2);
				request.getRequestDispatcher(WebPaths.USER_ITEMS_SERVLET).forward(request, response);
			} else {
				if (UserUtils.isLoginAvailable(userDTO2.getLogin())) {
					Context.getInstance().getUserProfileService().setUserDTO(userDTO2);
					UserUtils.createSession(request, userDTO2);
					request.getRequestDispatcher(WebPaths.USER_ITEMS_JSP).forward(request, response);
				} else {
					request.setAttribute(Attributes.MESSAGE, Messages.LOGIN_UNAVAILABLE);
					request.getRequestDispatcher(WebPaths.USER_ITEMS_SERVLET).forward(request, response);
				}
			}
		} else {
			request.getRequestDispatcher(WebPaths.USER_PROFILE_JSP).forward(request, response);
		}
	}
	
	


}
