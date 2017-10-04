package com.ohhay.base.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ThoaiNH
 * create 12/09/2014
 * load .properties file
 */
public class PropertiesLoader {
	private Properties prop = new Properties();
	public static final String CONFIG = "config.properties";
	public static final String TEMPLATE = "templaterule.properties";

	public PropertiesLoader(String fileName) {
		super();
		String propFileName = fileName;
		InputStream inputStream = ApplicationConstant.class.getClassLoader()
				.getResourceAsStream(propFileName);
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getProperty(String key) {
		return prop.getProperty(key);

	}
}
