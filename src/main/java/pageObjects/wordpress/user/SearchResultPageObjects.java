package pageObjects.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUIs.wordpress.user.SearchResultPageUI;

public class SearchResultPageObjects extends AbstractPages{
	private WebDriver driver;
	
	public SearchResultPageObjects(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isPostTitleDisplayedOnHeader(String title) {
		try {
			waitElementVisible(driver, SearchResultPageUI.TITLE_IN_HEADER, title);
			return isElementDisplayed(driver, SearchResultPageUI.TITLE_IN_HEADER, title);
		} catch (Exception e) {
			return false;
		}
		
	}
}
