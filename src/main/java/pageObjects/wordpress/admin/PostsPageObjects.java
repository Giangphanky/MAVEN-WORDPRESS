package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import commons.PageGeneratorManager;
import pageUIs.wordpress.admin.NewEditPostPageUI;
import pageUIs.wordpress.admin.PostPageUI;

public class PostsPageObjects extends AbstractPages {
	private WebDriver driver;

	public PostsPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public NewEditPostPageObjects clickToAddNewButton() {
		waitElementClickable(driver, PostPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, PostPageUI.ADD_NEW_BUTTON);
		return PageGeneratorManager.getNewEditPostAdminPage(driver);
	}

	public boolean isValueDisplayedAtColumnTitleByRowNumber(String columnID, String rowNumber, String value) {
		waitElementVisible(driver, PostPageUI.DYNAMIC_CULUMN_NAME, columnID);
		String columnIndex = String.valueOf(countElementNumber(driver, PostPageUI.DYNAMIC_CULUMN_NAME, columnID) + 1);
		return isElementDisplayed(driver, PostPageUI.DYNAMIC_VALUE_AT_COLUMN_NAME_AND_ROW_NUMBER, rowNumber,
				columnIndex, value);
	}

	public NewEditPostPageObjects clickToPostDetailByTitleName(String title) {
		waitElementVisible(driver, PostPageUI.POST_TITLE_LINK, title);
		clickToElement(driver, PostPageUI.POST_TITLE_LINK, title);
		return PageGeneratorManager.getNewEditPostAdminPage(driver);
	}

	public void inputToSearchTextbox(String title) {
		waitElementVisible(driver, PostPageUI.SEARCH_POST_TEXTBOX);
		sendKeysToElement(driver, PostPageUI.SEARCH_POST_TEXTBOX, title);
	}

	public void clickToSearchPostButton() {
		waitElementVisible(driver, PostPageUI.SEARCH_POST_BUTTON);
		clickToElement(driver, PostPageUI.SEARCH_POST_BUTTON);
	}

	public boolean isValueDisplayedAtColumnIDByRowNumber(String titleColumn, String rowNumber, String valueAtRow) {
		if (isElementUndisplayed(driver, PostPageUI.DYNAMIC_CULUMN_NAME, titleColumn)) {
			return isElementDisplayed(driver, PostPageUI.DYNAMIC_VALUE_AT_COLUMN_NAME_AND_ROW_NUMBER, rowNumber, "1",
					valueAtRow);
		} else {
			waitElementVisible(driver, PostPageUI.DYNAMIC_CULUMN_NAME, titleColumn);
			String columnIndex = String
					.valueOf(countElementNumber(driver, PostPageUI.DYNAMIC_CULUMN_NAME, titleColumn) + 1);
			return isElementDisplayed(driver, PostPageUI.DYNAMIC_VALUE_AT_COLUMN_NAME_AND_ROW_NUMBER, rowNumber,
					columnIndex, valueAtRow);
		}
	}

	public boolean isMoveToTrashMessageDisplayed() {
		waitElementVisible(driver, PostPageUI.MOVE_TO_TRASH_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, PostPageUI.MOVE_TO_TRASH_SUCCESS_MESSAGE);
	}

	public boolean isNoPostFoundMessageDisplayed() {
		waitElementVisible(driver, PostPageUI.NO_FOUNDED_MESSAGE);
		return isElementDisplayed(driver, PostPageUI.NO_FOUNDED_MESSAGE);
	}

}
