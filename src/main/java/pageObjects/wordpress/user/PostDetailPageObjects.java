package pageObjects.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUIs.wordpress.user.AbstractPageUI;
import pageUIs.wordpress.user.PostPageUserUI;

public class PostDetailPageObjects extends AbstractPages{
	private WebDriver driver;
	
	public PostDetailPageObjects(WebDriver driver) {
		this.driver = driver;
	}	

	public boolean isCategoryNameDisplayed(String newPostCategory) {
		waitElementVisible(driver, PostPageUserUI.CATEGORY_NAME, newPostCategory);
		return isElementDisplayed(driver, PostPageUserUI.CATEGORY_NAME, newPostCategory);
	}

	public boolean isTitleNameDisplayed(String newPostTitle) {
		waitElementVisible(driver, PostPageUserUI.TITLE_NAME, newPostTitle);
		return isElementDisplayed(driver, PostPageUserUI.TITLE_NAME, newPostTitle);
	}

	public boolean isImageDisplayed(String featureImageName) {
		String[] fileName = featureImageName.split("\\.");
		waitElementVisible(driver, PostPageUserUI.IMAGE_NAME, fileName[0]);
		return isElementDisplayed(driver, PostPageUserUI.IMAGE_NAME, fileName[0])
				&& isImageLoaded(driver, PostPageUserUI.IMAGE_NAME, fileName[0]);
	}

	public boolean isContentDisplayed(String newPostContent) {
		waitElementVisible(driver, PostPageUserUI.CONTENT_NAME, newPostContent);
		return isElementDisplayed(driver, PostPageUserUI.CONTENT_NAME, newPostContent);
	}

	public boolean isDateCreatedDisplayed(String today) {
		waitElementVisible(driver, PostPageUserUI.DATE_VALUE, today);
		return isElementDisplayed(driver, PostPageUserUI.DATE_VALUE, today);
	}

	public boolean isAuthorDisplayed(String authorPost) {
		waitElementVisible(driver, PostPageUserUI.AUTHOR_VALUE, authorPost);
		return isElementDisplayed(driver, PostPageUserUI.AUTHOR_VALUE, authorPost);
	}

	public boolean isTagDisplayed(String newPostTags) {
		waitElementVisible(driver, PostPageUserUI.TAG_NAME, newPostTags);
		return isElementDisplayed(driver, PostPageUserUI.TAG_NAME, newPostTags);
	}
}
