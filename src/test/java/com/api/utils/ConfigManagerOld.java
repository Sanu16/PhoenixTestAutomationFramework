package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOld {
//WAP to read a property file from config.properties

	private static Properties prop = new Properties();
	
	private ConfigManagerOld() {
		
	}

	static {
		//perform the operation of loading of property file in the memory
		//as it is a static block it will be executed only once during class loading time
		File configFile = new File(System.getProperty("user.dir") +File.separator+ "src"+File.separator+"test"+File.separator+"resources"+File.separator+"config"+File.separator+"config.properties");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(configFile);
			prop.load(fileReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {

		// Special class in java known as Properties
		// Create an object of property class

		// Load the Properties file using the load()

		return prop.getProperty(key);
	}
}
