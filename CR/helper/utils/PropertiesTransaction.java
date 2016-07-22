package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTransaction {
	
	public static Properties readPropertyFile(String fpath){
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(new File (fpath));
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
	

}
