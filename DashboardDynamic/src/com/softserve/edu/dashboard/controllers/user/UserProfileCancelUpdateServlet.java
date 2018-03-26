package com.softserve.edu.dashboard.controllers.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.edu.dashboard.constants.Attributes;
import com.softserve.edu.dashboard.constants.WebPaths;
import com.softserve.edu.dashboard.dto.UserDTO;

@WebServlet(WebPaths.USER_CANCEL_SERVLET)
public class UserProfileCancelUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserProfileCancelUpdateServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet from UserProfileCancelUpdate");
		UserDTO userDTO = (UserDTO) request.getSession().getAttribute(Attributes.USER_DTO);
		if (userDTO != null) {
			request.getRequestDispatcher(WebPaths.USER_ITEMS_SERVLET).forward(request, response);
		} else {
			request.getRequestDispatcher(WebPaths.LOGIN_JSP).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost from UserProfileCancelUpdate");
		doGet(request, response);
	}

}
