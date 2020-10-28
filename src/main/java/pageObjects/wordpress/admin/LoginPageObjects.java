package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUIs.wordpress.admin.LoginPageUI;

public class LoginPageObjects extends AbstractPages{
	private WebDriver driver;

	public LoginPageObjects (WebDriver driver) {
		this.driver = driver;
	}

	public void inputToEmailTextbox(String value) {
		waitElementVisible(driver, LoginPageUI.emailTextbox);
		sendKeysToElement(driver, LoginPageUI.emailTextbox, value);
	}
	
	public void inputToPasswordTextbox(String value) {
		waitElementVisible(driver, LoginPageUI.passwordTextbox);
		sendKeysToElement(driver, LoginPageUI.passwordTextbox, value);
	}

	public DashboardPageObjects clickToLoginButton() {
		waitElementClickable(driver, LoginPageUI.LoginButton);
		clickToElement(driver, LoginPageUI.LoginButton);
		return new DashboardPageObjects(driver);
	}

	public void clickToContinueButton() {
		waitElementClickable(driver, LoginPageUI.ContinueButton);
		clickToElement(driver, LoginPageUI.ContinueButton);
	}

}
