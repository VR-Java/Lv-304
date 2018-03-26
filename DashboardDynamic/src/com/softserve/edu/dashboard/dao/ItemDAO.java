package com.softserve.edu.dashboard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.dashboard.entity.ItemEntity;

public class ItemDAO  extends ADAO_CRUD<ItemEntity>{

	public ItemDAO() {
		super();
	}
	
	@Override
	public String getQueryPrefix() {
		return "item";
	}

	@Override
	public ItemEntity createEntity(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String title = resultSet.getString("title");
		String description = resultSet.getString("description");
		Long userId = resultSet.getLong("userId");
		String status = resultSet.getString("status");
		return new ItemEntity(id, title, description, userId, status);
	}

	@Override
	public List<Object> getEntityParams(String operation, ItemEntity entity) {
		List<Object> params = new ArrayList<>();
		switch (operation) {
		case CREATE:
			params.add(entity.getId());
			params.add(entity.getTitle());
			params.add(entity.getDescription());
			params.add(entity.getUserId());
			params.add(entity.getStatus());
			break;
		case UPDATE:
			params.add(entity.getTitle());
			params.add(entity.getDescription());
			params.add(entity.getUserId());
			params.add(entity.getStatus());
			params.add(entity.getId());
		}
		return params;
	}


}
