package driverFactory;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
	protected WebDriver driver;
	protected abstract void createDriver();
	
	public void quitDriver() {
		if(driver != null) {
			driver.quit();
			driver = null;
		}
		
	}
	
	public WebDriver getDriver(String url) {
		if(driver == null) {
			createDriver();
		}
		driver.get(url);
		return driver;
	}

}
