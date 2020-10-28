package pageUIs.wordpress.user;

public abstract class AbstractPageUI {
	
	public static final String UP_LOAD_FILE_TYPE = "//input[@type='file']";
	public static final String DYNAMIC_LINK = "//div[@class='wp-menu-name' and text()='%s']";
	public static final String DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE = "//a[text()='%s']//parent::p[@class='post-categories']//following-sibling::h2[@class='post-title']/a[text()='%s']//parent::h2//following-sibling::p[@class='post-meta']/a[text()='%s']";
	public static final String DYNAMIC_IMAGE_POST_WITH_TITLE_IMAGE_NAME = " //a[@title='%s']/img[contains(@src,'%s')]";
	public static final String DYNAMIC_POST_TITLE = "//h2[@class='post-title']/a[text()='%s']";
	public static final String SEARCH_ICON = "//a[@class='search-toggle']";
	public static final String SEARCH_TEXTBOX = "//input[@type='search']";
	public static final String SEARCH_BUTTON = "//span[@class='fa fw fa-search']";
}
