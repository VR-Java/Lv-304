package com.softserve.edu.dashboard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.dashboard.entity.UserEntity;

public class UserDAO extends ADAO_CRUD<UserEntity> {

	public UserDAO() {
		super();
	}

	@Override
	public String getQueryPrefix() {
		return "user";
	}

	@Override
	public UserEntity createEntity(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String name = resultSet.getString("name");
		String login = resultSet.getString("login");
		String password = resultSet.getString("password");
		String email = resultSet.getString("email");
		return new UserEntity(id, name, login, password, email);
	}

	@Override
	public List<Object> getEntityParams(String operation, UserEntity entity) {
		List<Object> params = new ArrayList<>();
		switch (operation) {
		case CREATE:
			params.add(entity.getId());
			params.add(entity.getName());
			params.add(entity.getLogin());
			params.add(entity.getPassword());
			params.add(entity.getEmail());
			break;
		case UPDATE:
			params.add(entity.getName());
			params.add(entity.getPassword());
			params.add(entity.getEmail());
			params.add(entity.getId());
		}
		return params;
	}

}
