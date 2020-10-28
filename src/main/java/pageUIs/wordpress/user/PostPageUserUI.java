package pageUIs.wordpress.user;

public class PostPageUserUI {
	public static final String CATEGORY_NAME = "//p[@class='post-categories']/a[text()='%s']";
	public static final String TITLE_NAME = "//h1[@class='post-title' and text()='%s']";
	public static final String IMAGE_NAME = "//img[contains(@src,'%s')]";
	public static final String CONTENT_NAME = "//div[@class='post-content']/p[contains(text(),'%s')]";
	public static final String TAG_NAME = " //div[@class='post-tags']/a[contains(text(), '%s')]";
	public static final String DATE_VALUE = "  //span[@class='post-meta-date']/a[contains(text(),'%s')]";
	public static final String AUTHOR_VALUE = "//span[@class='post-meta-author']/a[text() = '%s']";

}
