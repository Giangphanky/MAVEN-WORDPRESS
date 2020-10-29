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
		waitElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, LoginPageUI.EMAIL_TEXTBOX, value);
	}
	
	public void inputToPasswordTextbox(String value) {
		waitElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, value);
	}

	public DashboardPageObjects clickToLoginButton() {
		waitElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return new DashboardPageObjects(driver);
	}

	public void clickToContinueButton() {
		waitElementClickable(driver, LoginPageUI.CONTINUE_BUTTON);
		clickToElement(driver, LoginPageUI.CONTINUE_BUTTON);
	}

	public boolean isErrorMessageDisplayed(String message) {
		System.out.println(castRestParameter(LoginPageUI.DYNAMIC_ERROR_MESSAGE, message));
		waitElementVisible(driver, LoginPageUI.DYNAMIC_ERROR_MESSAGE, message);
		return isElementDisplayed(driver, LoginPageUI.DYNAMIC_ERROR_MESSAGE, message);
	}

}
