package com.softserve.edu.dashboard.tools;

import com.softserve.edu.dashboard.dao.ItemDAO;
import com.softserve.edu.dashboard.dao.UserDAO;
import com.softserve.edu.dashboard.services.ItemServise;
import com.softserve.edu.dashboard.services.LoginService;
import com.softserve.edu.dashboard.services.UserItemsServise;
import com.softserve.edu.dashboard.services.UserProfileService;

 // Container with all Services and DAO objects
 // Must be 1 exemplar only --> Singletone

public class Context {
	
	private UserDAO userDAO;
	private ItemDAO itemDAO;
	
	private LoginService loginService;
	private UserProfileService userProfileService;
	private ItemServise itemServise;
	private UserItemsServise userItemsServise;
	
	private static volatile Context instance = null;
	
	private Context() {
		initComponents();
	}
	
	public static Context getInstance() {
		if (instance == null) {
			synchronized (Context.class) {
				if (instance == null) {
					instance = new Context();
				}
			}
		}
		return instance;
	}
	
	private void initComponents() {
		userDAO = new UserDAO();
		itemDAO = new ItemDAO();

		loginService = new LoginService(userDAO);
		userProfileService = new UserProfileService(userDAO);
		itemServise = new  ItemServise(itemDAO);
		userItemsServise = new UserItemsServise(userDAO, itemDAO);
	}

	// getters

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public UserProfileService getUserProfileService() {
		return userProfileService;
	}

	public ItemServise getItemServise() {
		return itemServise;
	}

	public UserItemsServise getUserItemsServise() {
		return userItemsServise;
	}
	
}
