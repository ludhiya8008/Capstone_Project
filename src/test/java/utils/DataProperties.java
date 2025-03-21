package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataProperties {
	
	    private static Properties properties;

	    static {
	        properties = new Properties();
	        try {
	            FileInputStream file = new FileInputStream("C:\\Users\\sampa\\eclipse-workspace\\Project_Luma\\src\\main\\java\\data\\testdata.properties");
	            properties.load(file);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public static String getProperty(String key) {
	        return properties.getProperty(key);
	    }
	}


