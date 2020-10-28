package com.wordpress.user;

import org.testng.annotations.Test;

//import com.beust.jcommander.Parameter;

import commons.AbstractPages;
import commons.AbstractTest;
import commons.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class User_01_Login extends AbstractTest {

	private WebDriver driver;
	

	@Parameters({"url, browser"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		log.info("Pre-condition Step 1: Open browser with url");
		driver = getBrowserDriver(browserName, url);
		
	}

	@BeforeMethod
	public void beforeTest() {
	}
	
	@Test(description = "Login with empty user")
	public void TC_01_Login_With_Empty_User() {
		
	}
	
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}
