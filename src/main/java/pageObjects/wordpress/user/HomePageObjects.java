package pageObjects.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import commons.PageGeneratorManager;
import pageUIs.wordpress.user.PostPageUserUI;

public class HomePageObjects extends AbstractPages{
	private WebDriver driver;
	
	public HomePageObjects(WebDriver driver) {
		this.driver = driver;
	}
	
}
