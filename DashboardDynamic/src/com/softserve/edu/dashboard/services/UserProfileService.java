package com.softserve.edu.dashboard.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.dashboard.dao.UserDAO;
import com.softserve.edu.dashboard.dto.LoginDTO;
import com.softserve.edu.dashboard.dto.UserDTO;
import com.softserve.edu.dashboard.entity.UserEntity;

public class UserProfileService {

	private UserDAO userDAO;

	public UserProfileService() {
		this.userDAO = new UserDAO();
	}

	public UserProfileService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserDTO getUserDTO(String login) {
		UserEntity userEntity;
		if (isExistLogin(login)) {
			try {
				userEntity = userDAO.findByField(UserEntity.USER_ENTITY_LOGIN, login).get(0);
				return new UserDTO(userEntity.getId(), userEntity.getName(), userEntity.getLogin(),
						userEntity.getPassword(), userEntity.getEmail());
			} catch (SQLException e) {
				// TODO handle Exception
				// e.printStackTrace();
				System.out.println("User not found. SQLException: " + e.getMessage());
				return null;
			}
		}
		return null;
	}

	public void setUserDTO(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity(userDTO.getId(), userDTO.getName(), userDTO.getLogin(),
				userDTO.getPassword(), userDTO.getEmail());
		System.out.println(userEntity); // +

		if (userDTO.getId() < 0) {
			if (!isExistLogin(userEntity.getLogin())) {
				try {
					userDAO.create(userEntity);
					System.out.println("User " + userEntity + " created");
				} catch (SQLException e) {
					// TODO handle Exception
					// e.printStackTrace();
					System.out.println("User was not created. SQLException: " + e.getMessage());
				}
			}
		} else {
			try {
				userDAO.update(userEntity);
				System.out.println("User " + userEntity + " updated");
			} catch (SQLException e) {
				// TODO handle Exception
				// e.printStackTrace();
				System.out.println("User was not updated. SQLException: " + e.getMessage());
			}
		}
	}

	public boolean isExistLogin(String login) {
		boolean result = true;
		List<UserEntity> list;
		try {
			list = userDAO.findByField(UserEntity.USER_ENTITY_LOGIN, login);
			if (list.isEmpty()) {
				System.out.println("Login " + login + " doesn't exists");
				return false;
			}
			System.out.println("Login " + login + " exists already");
		} catch (SQLException e) {
			// TODO handle Exception
			// e.printStackTrace();
			System.out.println("User not found. SQLException: " + e.getMessage());
		}
		return result;
	}
	
	
	public void deleteUserDTO(UserDTO userDTO) {
		deleteUserDTOById(userDTO.getId());
	}
	
	public void deleteUserDTOById(Long id) {
		try {
			userDAO.deleteById(id);
			System.out.println("User deleted succsessfully");
		} catch (SQLException e) {
			// TODO handle Exception
			// e.printStackTrace();
			System.out.println("User not found. SQLException: " + e.getMessage());
		}
	}
	
	
	
	
	public boolean isLogged(String login, String password) {
		List<UserEntity> users = new ArrayList<>();
		try {
			users = userDAO.findByField(UserEntity.USER_ENTITY_LOGIN, login);
		} catch (SQLException e) {
			// TODO handle Exception
			System.out.println("User " + login + " not found. SQLException: " + e.getMessage());
			return false;
		}
		if(users.size()==1) {
			if(users.get(0).getPassword().equals(password)) {
				System.out.println("user " + login + " is logged");
				return true;
			}
			System.out.println("Wrong password");
		}
		return false;
	}

}
