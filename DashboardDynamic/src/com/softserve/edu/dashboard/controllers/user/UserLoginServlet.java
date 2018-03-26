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

@WebServlet({ WebPaths.HOME_SERVLET, WebPaths.LOGIN_SERVLET })
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserLoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet from UserLoginServlet");
		request.getRequestDispatcher(WebPaths.LOGIN_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost from UserLoginServlet");
		UserDTO userDTO = (UserDTO) request.getSession().getAttribute(Attributes.USER_DTO);
		boolean result = UserUtils.isLogged(request, userDTO);
		if (result && request.getAttribute(Attributes.LOGIN) != null
				&& !((String) request.getAttribute(Attributes.LOGIN)).isEmpty()) {
			String login = (String) request.getAttribute(Attributes.LOGIN);
			request.getSession(true).setAttribute(Attributes.USER_DTO,
					Context.getInstance().getUserProfileService().getUserDTO(login));
			request.getRequestDispatcher(WebPaths.USER_ITEMS_SERVLET).forward(request, response);
		} else {
			request.setAttribute(Attributes.MESSAGE, Messages.INVALID_LOGN);
			request.getRequestDispatcher(WebPaths.LOGIN_JSP).forward(request, response);
		}
	}

}
