package pageUIs.wordpress.admin;

public class NewEditPostPageUI {
	public static final String ADD_TITLE_TEXTAREA = "//textarea[@id='post-title-0']";
	public static final String ADD_CONTENT_AREA = "//textarea[text()='Start writing or type / to choose a block']";
	public static final String ADD_CONTENT_AREA_PARAGRAPH = "//p[@data-title='Paragraph']";
	public static final String POST_TAB_BUTTON = "//button[text()='Post']";
	public static final String DYNAMIC_MENU_BUTTON = "//button[text()='%s']";
	public static final String CATEGORY_CHECKBOX = "//label[text()='%s']//preceding-sibling::span//input";
	public static final String TAG_TEXTBOX = "//label[text()='Add New Tag']/following-sibling::div//input";
	public static final String TAG_DIV = "//label[text()='Add New Tag']/following-sibling::div";
	public static final String SET_FEATURED_IMAGE_TEXTBOX = "//button[text()='Set featured image']";
	public static final String UPLOAD_FILES_BUTTON_TAB= "//div[@id='__wp-uploader-id-2']//button[text()='Upload files']";
	public static final String SET_FEATURE_FILES_BUTTON= "//button[text()='Select']";
	public static final String DYNAMIC_IMAGE_UPLOADED_THUMBNAIL= "//img[@class='components-responsive-wrapper__content' and (contains(@src,'%s'))]";
	public static final String PUBLISH_BUTTON= "//div[@class='edit-post-header']//button[text()='Publish']";
	public static final String CONFIRM_PUBLISH_TEXT_FIELD= "//strong[text()='Are you ready to publish?']";
	public static final String POST_ON_LIVE_MESSAGE= "//div[contains(text(),'is now live.')]";
	public static final String DOUBLE_CHECK_PUBLISH_BUTTON= "//p[text()='Double-check your settings before publishing.']//ancestor::div[@class='editor-post-publish-panel__content']//preceding-sibling::div[@class='editor-post-publish-panel__header']//button[text()='Publish']";
	public static final String POST_PUBLISHED_ALERT= "//a[text()='View Post']//ancestor::div[contains(text(),'%s')]";
	public static final String DYNAMIC_TAGS_SPAN = "//span[text()='%s']";
	public static final String UPDATE_BUTTON = "//button[text()='Update' and @aria-disabled = 'false' ]";
	public static final String DYNAMIC_REMOVE_TAG = "//span[contains(text(),'%s')]//parent::span//following-sibling::button[@aria-label='Remove Tag']";
	public static final String DISABLED_UPDATE_BUTTON = "//button[text()='Update' and @aria-disabled = 'true' ]";
	public static final String MOVE_TO_TRASH_BUTTON = "//button[text()='Move to trash']";
}
 