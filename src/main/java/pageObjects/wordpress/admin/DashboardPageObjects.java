package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUIs.wordpress.admin.DashboardPageUI;

public class DashboardPageObjects extends AbstractPages{
	private WebDriver driver;

	public DashboardPageObjects (WebDriver driver) {
		this.driver = driver;
	}

	public boolean isHeaderTextDisplayed() {
		return isElementDisplayed(driver, DashboardPageUI.headerDashboardText);
	}
	

}
