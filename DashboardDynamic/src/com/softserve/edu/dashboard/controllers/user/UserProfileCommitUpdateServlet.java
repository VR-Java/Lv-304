package com.softserve.edu.dashboard.controllers.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.edu.dashboard.constants.WebPaths;

@WebServlet(WebPaths.USER_COMMIT_SERVLET)
public class UserProfileCommitUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserProfileCommitUpdateServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet from UserProfileCommitUpdate");
		request.getRequestDispatcher(WebPaths.USER_ITEMS_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost from UserProfileCommitUpdate");
		request.getRequestDispatcher(WebPaths.USER_ITEMS_SERVLET).forward(request, response);
	}

}
