package pageObjects.wordpress.admin;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.server.handler.ClickElement;

import commons.AbstractPages;
import commons.PageGeneratorManager;
import pageUIs.wordpress.admin.NewEditPostPageUI;

public class NewEditPostPageObjects extends AbstractPages {
	private WebDriver driver;

	public NewEditPostPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToTitleTextbox(String string) {
		// TODO Auto-generated method stub

	}

	public void inputToPostContentTextbox(String content) {
		waitElementVisible(driver, NewEditPostPageUI.ADD_CONTENT_AREA);
		clickToElement(driver, NewEditPostPageUI.ADD_CONTENT_AREA);
		waitElementClickable(driver, NewEditPostPageUI.ADD_CONTENT_AREA_PARAGRAPH);
		sendKeysToElement(driver, NewEditPostPageUI.ADD_CONTENT_AREA_PARAGRAPH, "");
		sendKeysToElement(driver, NewEditPostPageUI.ADD_CONTENT_AREA_PARAGRAPH, content);
	}

	public void editToPostContentTextbox(String editPostContent) {
		waitElementVisible(driver, NewEditPostPageUI.ADD_CONTENT_AREA_PARAGRAPH);
		sendKeysToElement(driver, NewEditPostPageUI.ADD_CONTENT_AREA_PARAGRAPH, editPostContent);
	}

	public void clickToUpdateButton() {
		waitElementClickable(driver, NewEditPostPageUI.UPDATE_BUTTON);
		clickToElementByJS(driver, NewEditPostPageUI.UPDATE_BUTTON);
	}

	public PostsPageObjects clickToMoveToTrashButton() {
		waitElementClickable(driver, NewEditPostPageUI.MOVE_TO_TRASH_BUTTON);
		clickToElement(driver, NewEditPostPageUI.MOVE_TO_TRASH_BUTTON);
		return PageGeneratorManager.getPostAdminPage(driver);
	}

	public void clickToPostTab() {
		waitElementClickable(driver, NewEditPostPageUI.POST_TAB_BUTTON);
		clickToElement(driver, NewEditPostPageUI.POST_TAB_BUTTON);
	}

	public void clickToOpenDetailMenuByText(String name) {
		waitElementClickable(driver, NewEditPostPageUI.DYNAMIC_MENU_BUTTON, name);
		clickToElement(driver, NewEditPostPageUI.DYNAMIC_MENU_BUTTON, name);
	}

	public void clickToCloseDetailMenuByText(String name) {
		waitElementClickable(driver, NewEditPostPageUI.DYNAMIC_MENU_BUTTON, name);
		clickToElement(driver, NewEditPostPageUI.DYNAMIC_MENU_BUTTON, name);
	}

	public void inputToTagTextbox(String tagName) {
		waitElementVisible(driver, NewEditPostPageUI.TAG_TEXTBOX);
		sendKeysToElement(driver, NewEditPostPageUI.TAG_TEXTBOX, tagName + ",");
	}

	public void clickToSetFeatureImageLink() {
		waitElementClickable(driver, NewEditPostPageUI.SET_FEATURED_IMAGE_TEXTBOX);
		clickToElementByJS(driver, NewEditPostPageUI.SET_FEATURED_IMAGE_TEXTBOX);
	}

	public void clickToUploadFileTab() {
		waitElementClickable(driver, NewEditPostPageUI.UPLOAD_FILES_BUTTON_TAB);
		clickToElement(driver, NewEditPostPageUI.UPLOAD_FILES_BUTTON_TAB);
	}

	public void clickToSetFeatureImageButton() {
		waitElementClickable(driver, NewEditPostPageUI.SET_FEATURE_FILES_BUTTON);
		clickToElement(driver, NewEditPostPageUI.SET_FEATURE_FILES_BUTTON);
	}

	public boolean isFeatureImageUploadSuccess(String imageName) {
		String[] fileName = imageName.split("\\.");
		scrollToElement(driver, NewEditPostPageUI.DYNAMIC_IMAGE_UPLOADED_THUMBNAIL, fileName[0]);
		return isElementDisplayed(driver, NewEditPostPageUI.DYNAMIC_IMAGE_UPLOADED_THUMBNAIL, fileName[0]);
	}

	public void clickToPublishButton() {
		waitElementClickable(driver, NewEditPostPageUI.PUBLISH_BUTTON);
		clickToElement(driver, NewEditPostPageUI.PUBLISH_BUTTON);
	}

	public boolean isConfirmPublishMessageDisplayed() {
		return isElementDisplayed(driver, NewEditPostPageUI.CONFIRM_PUBLISH_TEXT_FIELD);
	}

	public boolean isMessagePostOnLiveDisplayed() {
		scrollToElement(driver, NewEditPostPageUI.POST_ON_LIVE_MESSAGE);
		return isElementDisplayed(driver, NewEditPostPageUI.POST_ON_LIVE_MESSAGE);
	}

	public void clickToDoubleCheckPublishButton() {
		waitElementClickable(driver, NewEditPostPageUI.DOUBLE_CHECK_PUBLISH_BUTTON);
		clickToElement(driver, NewEditPostPageUI.DOUBLE_CHECK_PUBLISH_BUTTON);
	}

	public boolean isMessagePostPublishedDisplayed(String message) {
		waitElementVisible(driver, NewEditPostPageUI.POST_PUBLISHED_ALERT, message);
		return isElementDisplayed(driver, NewEditPostPageUI.POST_PUBLISHED_ALERT, message);
	}

	public boolean isTagInputSuccess(String newPostTags) {
		waitElementVisible(driver, NewEditPostPageUI.DYNAMIC_TAGS_SPAN, newPostTags);
		return isElementDisplayed(driver, NewEditPostPageUI.DYNAMIC_TAGS_SPAN, newPostTags);
	}

	public void deSelectCategoryCheckbox(String newPostCategory) {
		waitElementClickable(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, newPostCategory);
		scrollToElement(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, newPostCategory);
		unCheckToCheckBox(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, newPostCategory);
	}

	public void selectCategoryCheckbox(String checkboxLabelText) {
		waitElementClickable(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, checkboxLabelText);
		scrollToElement(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, checkboxLabelText);
		checkToCheckBox(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, checkboxLabelText);
	}

	public void clickToDeleteTagButton(String newPostTags) {
		waitElementClickable(driver, NewEditPostPageUI.DYNAMIC_REMOVE_TAG, newPostTags);
		clickToElement(driver, NewEditPostPageUI.DYNAMIC_REMOVE_TAG, newPostTags);
	}

	public void inputToPostTitleTextbox(String title) {
		waitElementVisible(driver, NewEditPostPageUI.ADD_TITLE_TEXTAREA);
		sendKeysToElement(driver, NewEditPostPageUI.ADD_TITLE_TEXTAREA, title);
	}

	public void editToPostTitleTextbox(String editPostTitle) {
		waitElementVisible(driver, NewEditPostPageUI.ADD_TITLE_TEXTAREA);
		sendKeyBoardToElementToClear(driver, NewEditPostPageUI.ADD_TITLE_TEXTAREA);
		sendKeysToElement(driver, NewEditPostPageUI.ADD_TITLE_TEXTAREA, editPostTitle);
	}

	public boolean isUpdateButtonDisabled() {
		try {
			waitElementVisible(driver, NewEditPostPageUI.DISABLED_UPDATE_BUTTON);
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	public void checkIfUpdateButtonClickAgain() {
		try {
		if(isElementDisplayed(driver, NewEditPostPageUI.UPDATE_BUTTON) == true){
			clickToElement(driver, NewEditPostPageUI.UPDATE_BUTTON);
		}
		}catch(Exception e) {
			e.getMessage();
		}
	}

	public boolean isUpdateButtonDisable() {
		try {
		waitElementVisible(driver, NewEditPostPageUI.DISABLED_UPDATE_BUTTON);
		return isElementDisplayed(driver, NewEditPostPageUI.DISABLED_UPDATE_BUTTON);
		}catch(Exception e)
		{
			return false;
		}
	}
}
