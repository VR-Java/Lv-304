package com.softserve.edu.dashboard.services;

import java.sql.SQLException;

import com.softserve.edu.dashboard.dao.ItemDAO;
import com.softserve.edu.dashboard.dto.ItemDTO;
import com.softserve.edu.dashboard.entity.ItemEntity;

public class ItemServise {

	private ItemDAO itemDAO;

	public ItemServise() {
		this.itemDAO = new ItemDAO(); // bad practice
	}

	public ItemServise(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}
	
	
	public ItemDTO getItemDTO(long id) {
		ItemEntity itemEntity;
		try {
			itemEntity = itemDAO.findById(id);
			return new ItemDTO(
							itemEntity.getId(), 
							itemEntity.getTitle(), 
							itemEntity.getDescription(),
							itemEntity.getUserId(), 
							itemEntity.getStatus());
		} catch (SQLException e) {
			// TODO handle Exception
			// e.printStackTrace();
			System.out.println("Item not found. SQLException: " + e.getMessage());
			return null;
		}
	}

	public void setItemDTO(ItemDTO itemDTO) {
		ItemEntity itemEntity = new ItemEntity(
										itemDTO.getIdItem(), 
										itemDTO.getTitle(), 
										itemDTO.getDescription(),
										itemDTO.getIdUser(), 
										itemDTO.getStatus());
		if (itemDTO.getIdItem() < 0) { // if itemDTO.getIdItem == -1, we are going to create new Item
			try {
				itemDAO.create(itemEntity);
				System.out.println("Item " + itemEntity + " created");
			} catch (SQLException e) {
				// TODO handle Exception
				// e.printStackTrace();
				System.out.println("Item was not created. SQLException: " + e.getMessage());
			}
		} else {
			try {
				itemDAO.update(itemEntity);
				System.out.println("Item " + itemEntity + " update");
			} catch (SQLException e) {
				// TODO handle Exception
				// e.printStackTrace();
				System.out.println("Item was not updated. SQLException: " + e.getMessage());
			}
		}
	}
	
	public boolean isExistItem(long id) {
		boolean result = true;
		try {
			itemDAO.findById(id);
		} catch (SQLException e) {
			// TODO handle Exception
			System.out.println("Item not found, message: " + e.getMessage());
			result = false;
		}
		return result;
	}
	
	
	public void deleteItemDTO(ItemDTO itemDTO) {
		deleteItemDTOById(itemDTO.getIdItem());
	}
	
	public void deleteItemDTOById(Long id) {
		try {
			if(isExistItem(id)) {
				itemDAO.deleteById(id);
				System.out.println("Item deleted succsessfully");				
			} else {
				System.out.println("Item doesn't exists");
			}
		} catch (SQLException e) {
			// TODO handle Exception
			// e.printStackTrace();
			System.out.println("Item not found. SQLException: " + e.getMessage());
		}
	}

}
