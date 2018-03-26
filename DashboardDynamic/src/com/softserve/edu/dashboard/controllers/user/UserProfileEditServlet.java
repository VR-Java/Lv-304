package com.softserve.edu.dashboard.controllers.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.edu.dashboard.constants.Attributes;
import com.softserve.edu.dashboard.constants.WebPaths;
import com.softserve.edu.dashboard.dto.LoginDTO;
import com.softserve.edu.dashboard.dto.UserDTO;
import com.softserve.edu.dashboard.tools.Context;
import com.softserve.edu.dashboard.tools.UserUtils;

@WebServlet(WebPaths.USER_EDIT_SERVLET)
public class UserProfileEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserProfileEditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet from UserProfileEditServlet");
		UserDTO userDTO = (UserDTO) request.getSession().getAttribute(Attributes.USER_DTO);
		if (userDTO != null) {
			request.setAttribute(Attributes.USER_DTO, userDTO);
			
			//TODO check if works
			request.getRequestDispatcher(WebPaths.USER_PROFILE_JSP).forward(request, response);
		} else {
			request.getRequestDispatcher(WebPaths.LOGIN_JSP).forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("doPost from UserProfileEditServlet");
		
//		UserDTO userDTO = (UserDTO) request.getSession().getAttribute(Attributes.USER_DTO);
//		
//		if (userDTO != null) {
//			String login = userDTO.getLogin();
//			
//			
//		}
//		
//		
//		UserDTO userDTO2 = UserUtils.initUserDTO(request, null);
//		if (userDTO != null) {
//			Context.getInstance().getUserProfileService().setUserDTO(userDTO);
////			request.setAttribute(Attributes.USER_DTO, userDTO);
//			request.getSession(true).setAttribute(Attributes.USER_DTO, userDTO);
//			System.out.println("Session created for user " + userDTO.getLogin());
//			request.getRequestDispatcher(WebPaths.USER_ITEMS_SERVLET).forward(request, response);
//		} else {
//			request.getRequestDispatcher(WebPaths.USER_PROFILE_JSP).forward(request, response);
//		}
//	}
		
		
		doPost(request, response);

	}

}
