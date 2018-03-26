package com.softserve.edu.dashboard.tools;

import javax.servlet.http.HttpServletRequest;

import com.softserve.edu.dashboard.constants.Attributes;
import com.softserve.edu.dashboard.constants.Messages;
import com.softserve.edu.dashboard.constants.WebPaths;
import com.softserve.edu.dashboard.dto.UserDTO;
import com.softserve.edu.dashboard.dto.UserItemsDTO;

public class UserUtils {

	public static boolean isLogged(HttpServletRequest request, UserDTO userDTO) {
		boolean result = false;
		String login = checkLogin(request, userDTO);
		System.out.println("login"+login);
		String password = checkPassword(request, userDTO);
		System.out.println("password"+password);

		if (login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
			result = Context.getInstance().getUserProfileService().isLogged(login, password);
			request.setAttribute(Attributes.LOGIN, login);
		}
		return result;
	}

	public static UserDTO initUserDTO(HttpServletRequest request, UserDTO userDTO) {
		System.out.println("UserUtils, userDTO:" + userDTO);
		
		Long userId;
		if (userDTO != null) {
			userId = userDTO.getId();
		} else {
			userId = -1L;
		}
		String login = checkLogin(request, userDTO);
		System.out.println(login);
		String name = checkName(request, userDTO);
		String password = checkPassword(request, userDTO);
		String confirmpassword = checkConfirmPassword(request, userDTO);
		String email = checkEmail(request, userDTO);
		if (login != null && !login.isEmpty() && name != null && !name.isEmpty() && password != null
				&& !password.isEmpty() && email != null && !email.isEmpty()) {
			if (confirmpassword != null && !confirmpassword.isEmpty() && password.equals(confirmpassword)) {
				UserDTO userDTO2 = new UserDTO(userId, name, login, password, email);
//				Context.getInstance().getUserProfileService().setUserDTO(userDTO2);
//				request.setAttribute(Attributes.USER_DTO, userDTO2);
				return userDTO2;
			} else {
				request.setAttribute(Attributes.MESSAGE, Messages.PASSWORDS_NOT_MATCH);
				return null;
			}
		} else {
			request.setAttribute(Attributes.MESSAGE, Messages.NOT_FILLED);
			return null;
		}
	}
	
	
	public static void createSession(HttpServletRequest request, UserDTO userDTO) {
		UserDTO userDTO2 = null;
		if (userDTO != null) {
			userDTO2 = Context.getInstance().getUserProfileService().getUserDTO(userDTO.getLogin());
			if(((UserDTO) request.getSession().getAttribute(Attributes.USER_DTO)) != null) {
				request.getSession().setAttribute(Attributes.USER_DTO, userDTO2);
			} else {
				request.getSession(true).setAttribute(Attributes.USER_DTO, userDTO2);
				System.out.println("Session created for user " + userDTO2.getLogin());
			}
		} else {
			request.getSession(true).setAttribute(Attributes.USER_DTO, userDTO2);
		}
	}
	
	
	public boolean  isSession(HttpServletRequest request) {
		return ((UserDTO) request.getSession().getAttribute(Attributes.USER_DTO) != null ? true: false);
	}
	
	
	

	private static String checkLogin(HttpServletRequest request, UserDTO userDTO) {
		System.out.println("checking login...");
		if (userDTO != null) {
			System.out.println("we are here");
			return userDTO.getLogin(); // if logged - impossible to change login!
		} else if (request.getParameter(Attributes.LOGIN) != null
				&& !request.getParameter(Attributes.LOGIN).isEmpty()) {
//			if (!Context.getInstance().getUserProfileService().isExistLogin(request.getParameter(Attributes.LOGIN))) {
				return request.getParameter(Attributes.LOGIN);
//			} else {
//				request.setAttribute(Attributes.MESSAGE, Messages.LOGIN_UNAVAILABLE);
//				return null;
//			}
		} else if ((String) request.getAttribute(Attributes.LOGIN) != null
				&& !((String) request.getAttribute(Attributes.LOGIN)).isEmpty()) {
//			if (!Context.getInstance().getUserProfileService().isExistLogin(request.getParameter(Attributes.LOGIN))) {
				return (String) request.getAttribute(Attributes.LOGIN);
//			} else {
//				request.setAttribute(Attributes.MESSAGE, Messages.LOGIN_UNAVAILABLE);
//				return null;
//			}
		} else {
			System.out.println("login = null");
			return null;
		}
	}
	
	
	public static boolean isLoginAvailable(String login) {
		return (Context.getInstance().getUserProfileService().isExistLogin(login)) ? false : true;
	}

	private static String checkPassword(HttpServletRequest request, UserDTO userDTO) {
		if (request.getParameter(Attributes.PASSWORD) != null && !request.getParameter(Attributes.PASSWORD).isEmpty()) {
			return request.getParameter(Attributes.PASSWORD);
		} else if ((String) request.getAttribute(Attributes.PASSWORD) != null
				&& !((String) request.getAttribute(Attributes.PASSWORD)).isEmpty()) {
			return (String) request.getAttribute(Attributes.PASSWORD);
		} else if (userDTO != null) {
			return userDTO.getPassword();
		} else {
			return null;
		}
	}

	private static String checkConfirmPassword(HttpServletRequest request, UserDTO userDTO) {
		if (request.getParameter(Attributes.CONFIRMPASSWORD) != null
				&& !request.getParameter(Attributes.CONFIRMPASSWORD).isEmpty()) {
			return request.getParameter(Attributes.CONFIRMPASSWORD);
		} else if ((String) request.getAttribute(Attributes.CONFIRMPASSWORD) != null
				&& !((String) request.getAttribute(Attributes.CONFIRMPASSWORD)).isEmpty()) {
			return (String) request.getAttribute(Attributes.CONFIRMPASSWORD);
		} else {
			return null;
		}
	}

	private static String checkName(HttpServletRequest request, UserDTO userDTO) {
		if (request.getParameter(Attributes.NAME) != null && !request.getParameter(Attributes.NAME).isEmpty()) {
			return request.getParameter(Attributes.NAME);
		} else if ((String) request.getAttribute(Attributes.NAME) != null
				&& !((String) request.getAttribute(Attributes.NAME)).isEmpty()) {
			return (String) request.getAttribute(Attributes.NAME);
		} else if (userDTO != null) {
			return userDTO.getName();
		} else {
			return null;
		}
	}

	private static String checkEmail(HttpServletRequest request, UserDTO userDTO) {
		if (request.getParameter(Attributes.EMAIL) != null && !request.getParameter(Attributes.EMAIL).isEmpty()) {
			return request.getParameter(Attributes.EMAIL);
		} else if ((String) request.getAttribute(Attributes.EMAIL) != null
				&& !((String) request.getAttribute(Attributes.EMAIL)).isEmpty()) {
			return (String) request.getAttribute(Attributes.EMAIL);
		} else if (userDTO != null) {
			return userDTO.getEmail();
		} else {
			return null;
		}
	}

}
