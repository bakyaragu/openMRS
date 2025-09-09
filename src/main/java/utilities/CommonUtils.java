package utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import constants.Constants;

public class CommonUtils {

	public static void loadProperties() throws FileNotFoundException {
		FileReader filereader = new FileReader("config.properties");
		Properties properties = new Properties();
		try {
			properties.load(filereader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	Constants.APP_URL=	properties.getProperty("APP_URL");
	Constants.BROWSER=	properties.getProperty("BROWSER");
	Constants.CHROME_DRIVER_PATH = properties.getProperty("CHROME_DRIVER_PATH");
	Constants.USERNAME = properties.getProperty("USERNAME");
	Constants.PASSWORD = properties.getProperty("PASSWORD");
	Constants.LOCATION = properties.getProperty("LOCATION");

	}
}
