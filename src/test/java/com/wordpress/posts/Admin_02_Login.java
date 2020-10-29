package com.wordpress.posts;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.wordpress.admin.DashboardPageObjects;
import pageObjects.wordpress.admin.LoginPageObjects;
import testdata.LoginData;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Admin_02_Login extends AbstractTest {

	@BeforeClass
	public void beforeClass() {
		
	}
	
	@Parameters({"browser" , "url"})
	@BeforeTest
	public void BeforeTest(String browserName, String url) {
		log.info("Pre-condition Step 1: Open browser with url");
		driver = getBrowserDriver(browserName, url);
		

		log.info("Pre-condition Step 2: Create Home Page");
		loginAdminPage = PageGeneratorManager.getLoginAdminPage(driver);
	}

	@Test(description = "Login with empty username")
	public void TC_01_Login_With_Empty_Username() {
		log.info("Login page - Step 1: Input nothing to username");
		loginAdminPage.inputToEmailTextbox("");

		log.info("Login page - Step 2: Click to Continue button");
		loginAdminPage.clickToContinueButton();
		
		log.info("Login page - Step 3: Verify error message displayed");
		verifyTrue(loginAdminPage.isErrorMessageDisplayed(LoginData.EMPTY_EMAIL_MESSAGE_TEXT));
	}

	@Test(description = "Login with not existed username")
	public void TC_02_Login_With_Not_Existed_Username() {
		log.info("Login page - Step 1: Input not existed to username");
		loginAdminPage.inputToEmailTextbox(LoginData.INVALID_USERNAME);

		log.info("Login page - Step 2: Click to Continue button");
		loginAdminPage.clickToContinueButton();
		
		log.info("Login page - Step 3: Verify invalid user message displayed");
		verifyTrue(loginAdminPage.isErrorMessageDisplayed(LoginData.INVALID_USERNAME));
	}

	@Test(description = "Login with invalid password")
	public void TC_03_Login_With_Invalid_Password() {
		log.info("Login page - Step 1: Input to username");
		loginAdminPage.inputToEmailTextbox(GlobalConstants.USER_NAME);

		log.info("Login page - Step 2: Click to Continue button");
		loginAdminPage.clickToContinueButton();

		log.info("Login page - Step 3: Input invalid pass to password");
		loginAdminPage.inputToPasswordTextbox(LoginData.INVALID_PASSWORD);

		log.info("Login page - Step 4: Click to Login button");
		loginAdminPage.clickToLoginButton();
		
		log.info("Login page - Step 5: Verify invalid pass displayed");
		verifyTrue(loginAdminPage.isErrorMessageDisplayed(LoginData.INVALID_PASSWORD));
	}

	@Test(description = "Login with valid password and username")
	public void TC_04_Login_With_Valid_Username_And_Password() {
		log.info("Login page - Step 1: Input to username");
		loginAdminPage.inputToEmailTextbox(GlobalConstants.USER_NAME);

		log.info("Login page - Step 2: Click to Continue button");
		loginAdminPage.clickToContinueButton();

		log.info("Login page - Step 3: Input valid pass to password");
		loginAdminPage.inputToPasswordTextbox(GlobalConstants.PASSWORD);
		
		log.info("Pre-condition Step 4: Click to 'Login' Button");
		dashboardPage = loginAdminPage.clickToLoginButton();

		log.info("Pre-condition Step 5: Verify header is displayed");
		verifyTrue(dashboardPage.isHeaderTextDisplayed());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-Condition Close browser");
		closeBrowserAndDriver(driver);
	}
	
	private WebDriver driver;
	LoginPageObjects loginAdminPage;
	DashboardPageObjects dashboardPage;

}
