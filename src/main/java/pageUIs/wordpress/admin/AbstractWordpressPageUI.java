package pageUIs.wordpress.admin;

public abstract class AbstractWordpressPageUI {
	
	public static final String UP_LOAD_FILE_TYPE = "//input[@type='file']";
	public static final String PAGES_LINK = "//div[@class='wp-menu-name' and text()='Pages'";
	public static final String POSTS_LINK = "//div[@class='wp-menu-name' and text()='Posts'";
	public static final String MEDIA_LINK = "//div[@class='wp-menu-name' and text()='Media'";
	
	
	public static final String DYNAMIC_LINK = "//div[@class='wp-menu-name' and text()='%s'";
	public static final String EDIT_TEXT_AFTER_UPLOADED_LINK = "//a[contains(text(),'Edit Image')]";
	public static final String ALL_UPLOADED_IMAGE = "//ul[@class='attachments ui-sortable ui-sortable-disabled']/li";
}
