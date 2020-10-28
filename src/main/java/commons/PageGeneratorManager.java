package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.wordpress.admin.DashboardPageObjects;
import pageObjects.wordpress.admin.LoginPageObjects;
import pageObjects.wordpress.admin.MediaPageObjects;
import pageObjects.wordpress.admin.NewEditPostPageObjects;
import pageObjects.wordpress.admin.PagesPageObjects;
import pageObjects.wordpress.admin.PostsPageObjects;
import pageObjects.wordpress.user.HomePageObjects;
import pageObjects.wordpress.user.PostDetailPageObjects;
import pageObjects.wordpress.user.SearchResultPageObjects;

public class PageGeneratorManager {
	
	public static PostDetailPageObjects getUserPostDetailPage(WebDriver driver) {
		return new PostDetailPageObjects(driver);
	}
	public static LoginPageObjects getLoginAdminPage(WebDriver driver) {
		return new LoginPageObjects(driver);
	}
	public static DashboardPageObjects getDashboardAdminPage(WebDriver driver) {
		return new DashboardPageObjects(driver);
	}
	public static MediaPageObjects getMediaAdminPage(WebDriver driver) {
		return new MediaPageObjects(driver);
	}
	public static NewEditPostPageObjects getNewEditPostAdminPage(WebDriver driver) {
		return new NewEditPostPageObjects(driver);
	}
	public static PagesPageObjects getPagesAdminPage(WebDriver driver) {
		return new PagesPageObjects(driver);
	}
	public static PostsPageObjects getPostAdminPage(WebDriver driver) {
		return new PostsPageObjects(driver);
	}
	public static HomePageObjects getUserHomePage(WebDriver driver) {
		return new HomePageObjects(driver);
	}
	public static SearchResultPageObjects getUserSearchResultPage(WebDriver driver) {
		return new SearchResultPageObjects(driver);
	}
}
