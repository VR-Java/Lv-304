package com.softserve.edu.dashboard.tools;

import java.io.FileInputStream;
import java.util.Properties;

public class SQLProperty {

	private static Properties properties;
	static {
		properties = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream(
					"D:\\Softserve\\Workspace\\DashboardDynamic\\src\\resources\\SQL.properties");
			// properties.load(SQLProperty.class.getClassLoader().getResourceAsStream("SQL.properties"));
			properties.load(fileInputStream);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO MyException
		}
	}

	public static String get(String name) {
		if (properties.containsKey(name)) {
			return properties.getProperty(name);
		}
		// TODO MyException
		throw new RuntimeException("Key not found");
	}
}
