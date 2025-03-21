package utils;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.configuration2.PropertiesConfiguration;

public class DataProperties1 {
	
	private static Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\sampa\\eclipse-workspace\\Magento\\src\\test\\java\\data\\config.properties");
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("Error loading properties file: " + e.getMessage());
        }
    }
    public static String getProperty(String key) {
    	return properties.getProperty(key, "");
    }
	    }



