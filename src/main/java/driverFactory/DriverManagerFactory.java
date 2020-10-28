package driverFactory;

import java.util.concurrent.TimeUnit;

import commons.Browser;

public class DriverManagerFactory {
	
	public static DriverManager getManager(String browserName) {
		Browser browser = Browser.valueOf(browserName.toUpperCase());
		DriverManager driverManager;
		if(browser == Browser.FIREFOX){
			driverManager = new FirefoxDriverManager();
		}else if (browser == Browser.CHROME) {
			driverManager = new ChromeDriverManager();
		}else {
			driverManager = new ChromeHeadlessDriverManager();
		}
		return driverManager;
	}

}
