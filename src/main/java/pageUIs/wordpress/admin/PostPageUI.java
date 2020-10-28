package pageUIs.wordpress.admin;

public class PostPageUI {
	//public static final String DYNAMIC_CULUMN_NAME = "//tr[@class='headings']//span[text()='%s']//ancestor::th/preceding-sibling::th";
	//public static final String DYNAMIC_TEXTBOX_BY_INDEX = "//tr[@class='filter']//th[%s]//input";
	//public static final String DYNAMIC_VALUE_AT_COLUMN_NAME_AND_ROW_NUMBER= "//tr[%s]//td[%s][contains(text(), '%s')]";
	public static final String DYNAMIC_CULUMN_NAME = "//th[@id='%s']//preceding-sibling::th";
	public static final String DYNAMIC_VALUE_AT_COLUMN_NAME_AND_ROW_NUMBER= "//tbody[@id='the-list']//tr[%s]//td[%s]//a[contains(text(),'%s')]";
	public static final String ADD_NEW_BUTTON = "//span[@id='split-page-title-action']//a[text()='Add New']";
	public static final String SEARCH_POST_TEXTBOX = "//input[@id='post-search-input']";
	public static final String SEARCH_POST_BUTTON = "//input[@id='search-submit']";
	public static final String POST_TITLE_LINK = "//td[@data-colname='Title']//a[text()='%s']";
	public static final String MOVE_TO_TRASH_SUCCESS_MESSAGE = "//p[contains(text(),'1 post moved to the Trash.')]";
	public static final String NO_FOUNDED_MESSAGE = "//td[text()='No posts found.']";
}
