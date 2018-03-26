package com.softserve.edu.dashboard.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.dashboard.dao.UserDAO;
import com.softserve.edu.dashboard.dto.LoginDTO;
import com.softserve.edu.dashboard.entity.UserEntity;

public class LoginService {

	private UserDAO userDAO;

	public LoginService() {
		userDAO = new UserDAO(); // Bad Solution
	}

	public LoginService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public boolean isLogged(LoginDTO loginDTO) {
		List<UserEntity> users = new ArrayList<>();
		try {
			// list of all users with loginDTO.getLogin()
			// only 1 element is expected
			users = userDAO.findByField(UserEntity.USER_ENTITY_LOGIN, loginDTO.getLogin());
//			users.forEach(System.out::println);
		} catch (SQLException e) {
			// TODO handle Exception
			System.out.println("User " + loginDTO.getLogin() + " not found. SQLException: " + e.getMessage());
			return false;
		}
		if(users.size()==1) {
			if(users.get(0).getPassword().equals(loginDTO.getPassword())) {
				System.out.println("user " + loginDTO.getLogin() + " is logged");
				return true;
			}
			System.out.println("Wrong password");
		}
		return false;
	}

}
