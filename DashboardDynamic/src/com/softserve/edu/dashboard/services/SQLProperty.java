package com.softserve.edu.dashboard.services;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class SQLProperty {

	private static Properties properties;
	static {
		properties = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream("D:\\Softserve\\Workspace\\DashboardDynamic\\src\\resources\\SQL.properties");
//			FileInputStream fileInputStream = new FileInputStream("SQL.properties");
			
//			InputStream is = getClass().getClassLoader().getResourceAsStream("SQL.properties");
//			properties.load(SQLProperty.class.getClassLoader().getResourceAsStream("SQL.properties"));

			properties.load(fileInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String name) {
		if (properties.containsKey(name)) {
			return properties.getProperty(name);
		}
		throw new RuntimeException("Key not found");
	}
}
