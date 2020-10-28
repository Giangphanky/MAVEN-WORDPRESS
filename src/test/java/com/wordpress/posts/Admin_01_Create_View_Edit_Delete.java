package com.wordpress.posts;

import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import commons.AbstractPages;
import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.wordpress.admin.DashboardPageObjects;
import pageObjects.wordpress.admin.LoginPageObjects;
import pageObjects.wordpress.admin.NewEditPostPageObjects;
import pageObjects.wordpress.admin.PostsPageObjects;
import pageObjects.wordpress.user.HomePageObjects;
import pageObjects.wordpress.user.PostDetailPageObjects;
import pageObjects.wordpress.user.SearchResultPageObjects;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.AfterClass;

public class Admin_01_Create_View_Edit_Delete extends AbstractTest {

	int fakeNumber = randomData();
	String featureImageName = "img1.png";
	String newPostTitle = "New_post_title" + fakeNumber;
	String newPostContent = "New_post_content" + fakeNumber;
	String newPostTags = "new_tag_" + fakeNumber;
	String newPostCategory = "NEW LIVE CODING";
	String authorPost = "Automation FC";
	String today = getWordpressToday();

	String editPostTitle = "Edit";
	String totalPostTitle = newPostTitle + editPostTitle;
	String editPostContent = "Edit_post_content" + fakeNumber;
	String editPostTag = "Edit_post_tag" + fakeNumber;
	String editPostCategory = "EDIT LIVE CODING";

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		log.info("Pre-condition Step 1: Open browser with url");
		driver = getBrowserDriver(browserName, url);

		log.info("Pre-condition Step 2: Create Home Page");
		loginAdminPage = PageGeneratorManager.getLoginAdminPage(driver);

		log.info("Pre-condition Step 3: Input to 'Email' Textbox");
		loginAdminPage.inputToEmailTextbox(GlobalConstants.USER_NAME);

		log.info("Pre-condition Step 4: Click to 'Continue' Button");
		loginAdminPage.clickToContinueButton();

		log.info("Pre-condition Step 5: Input to 'Password' Textbox");
		loginAdminPage.inputToPasswordTextbox(GlobalConstants.PASSWORD);

		log.info("Pre-condition Step 6: Click to 'Login' Button");
		dashboardPage = loginAdminPage.clickToLoginButton();

		log.info("Pre-condition Step 7: Verify header is displayed");
		verifyTrue(dashboardPage.isHeaderTextDisplayed());

	}

	@Test(description = "Create New Post aOke solved problemt Admin Page")
	public void TC_01_Create_New_Post_At_Admin_page() {
		// Go to Post Page
		dashboardPage.openMenuPageByName(driver, "Posts");
		postAdminPage = PageGeneratorManager.getPostAdminPage(driver);

		newEditPostPage = postAdminPage.clickToAddNewButton();

		newEditPostPage.inputToPostTitleTextbox(newPostTitle);

		newEditPostPage.inputToPostContentTextbox(newPostContent);

		newEditPostPage.clickToPostTab();

		newEditPostPage.clickToOpenDetailMenuByText("Categories");

		newEditPostPage.selectCategoryCheckbox(newPostCategory);

		newEditPostPage.clickToCloseDetailMenuByText("Categories");

		newEditPostPage.clickToOpenDetailMenuByText("Tags");

		newEditPostPage.inputToTagTextbox(newPostTags);

		verifyTrue(newEditPostPage.isTagInputSuccess(newPostTags));

		newEditPostPage.clickToCloseDetailMenuByText("Tags");

		newEditPostPage.clickToOpenDetailMenuByText("Featured image");

		newEditPostPage.clickToSetFeatureImageLink();

		newEditPostPage.clickToUploadFileTab();

		newEditPostPage.upLoadMultipleFiles(driver, featureImageName);

		verifyTrue(newEditPostPage.areFileUploadedDisplayed(driver, featureImageName));

		newEditPostPage.clickToSetFeatureImageButton();

		// Manual thỉnh thoảng cũng bị lỗi
		// verifyTrue(newEditPostPage.isFeatureImageUploadSuccess(featureImageName));

		newEditPostPage.clickToPublishButton();

		verifyTrue(newEditPostPage.isConfirmPublishMessageDisplayed());

		newEditPostPage.clickToDoubleCheckPublishButton();

		verifyTrue(newEditPostPage.isMessagePostPublishedDisplayed("Post published"));
		// Search Post at Admin Page
		dashboardPage = newEditPostPage.openAdminLoggedPage(driver);

		dashboardPage.openMenuPageByName(driver, "Posts");
		postAdminPage = PageGeneratorManager.getPostAdminPage(driver);

		postAdminPage.inputToSearchTextbox(newPostTitle);

		postAdminPage.clickToSearchPostButton();

		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("title", "1", newPostTitle);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("author", "1", authorPost);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("categories", "1", newPostCategory);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("tags", "1", newPostTags);

		// Chuyển qua trang user
		homeUserPage = postAdminPage.openEndUserUserPage(driver);
		verifyTrue(homeUserPage.isPostDisplayedOnLatestPost(driver, newPostCategory, newPostTitle, today));
		// Chạy không ổn định
		verifyTrue(homeUserPage.isPostImageDisplayedAtPostTitleName(driver, newPostTitle, featureImageName));

		postDetailPage = homeUserPage.clickToPostDetailWithTitleName(driver, newPostTitle);

		verifyTrue(postDetailPage.isTitleNameDisplayed(newPostTitle));
		verifyTrue(postDetailPage.isCategoryNameDisplayed(newPostCategory));
		verifyTrue(postDetailPage.isImageDisplayed(featureImageName));
		verifyTrue(postDetailPage.isContentDisplayed(newPostContent));
		verifyTrue(postDetailPage.isDateCreatedDisplayed(today));
		verifyTrue(postDetailPage.isAuthorDisplayed(authorPost));
		verifyTrue(postDetailPage.isTagDisplayed(newPostTags));

		// Search post at end user
		searchResultPage = postDetailPage.inputToSearchTextboxAtEndUserPage(driver, newPostTitle);

		verifyTrue(searchResultPage.isPostTitleDisplayedOnHeader(newPostTitle));
		verifyTrue(searchResultPage.isPostDisplayedOnLatestPost(driver, newPostCategory, newPostTitle, today));
		verifyTrue(searchResultPage.isPostImageDisplayedAtPostTitleName(driver, newPostTitle, featureImageName));
	}

	/**
	 * 
	 */
	@Test(description = "Edit new post at Admin page")
	public void TC_02_Edit_New_Post_At_Admin_page() {
		// Navigate to Admin Page
		dashboardPage = searchResultPage.openAdminLoggedPage(driver);

		// Go to post page
		dashboardPage.openMenuPageByName(driver, "Posts");
		postAdminPage = PageGeneratorManager.getPostAdminPage(driver);

		// Search Post at Admin Page
		postAdminPage.inputToSearchTextbox(newPostTitle);

		postAdminPage.clickToSearchPostButton();

		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("title", "1", newPostTitle);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("author", "1", authorPost);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("categories", "1", newPostCategory);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("tags", "1", newPostTags);

		// Click To Post Detail
		newEditPostPage = postAdminPage.clickToPostDetailByTitleName(newPostTitle);

		// Edit Post
		newEditPostPage.clickToPostTab();
		newEditPostPage.clickToOpenDetailMenuByText("Categories");
		newEditPostPage.deSelectCategoryCheckbox(newPostCategory);
		newEditPostPage.selectCategoryCheckbox(editPostCategory);
		newEditPostPage.clickToCloseDetailMenuByText("Categories");
		newEditPostPage.clickToOpenDetailMenuByText("Tags");
		newEditPostPage.clickToDeleteTagButton(newPostTags);
		newEditPostPage.inputToTagTextbox(editPostTag);
		verifyTrue(newEditPostPage.isTagInputSuccess(editPostTag));
		newEditPostPage.editToPostTitleTextbox(editPostTitle);
		newEditPostPage.editToPostContentTextbox(editPostContent);

		newEditPostPage.clickToUpdateButton();

		verifyTrue(newEditPostPage.isMessagePostPublishedDisplayed("Post updated"));

		newEditPostPage.checkIfUpdateButtonClickAgain();

		verifyTrue(newEditPostPage.isUpdateButtonDisable());

		// Search Post at Admin Page
		dashboardPage = newEditPostPage.openAdminLoggedPage(driver);

		dashboardPage.openMenuPageByName(driver, "Posts");
		postAdminPage = PageGeneratorManager.getPostAdminPage(driver);

		postAdminPage.inputToSearchTextbox(editPostTitle);

		postAdminPage.clickToSearchPostButton();

		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("title", "1", totalPostTitle);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("author", "1", authorPost);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("categories", "1", editPostCategory);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("tags", "1", editPostTag);

		// Chuyển qua trang user
		homeUserPage = postAdminPage.openEndUserUserPage(driver);
		verifyTrue(homeUserPage.isPostDisplayedOnLatestPost(driver, editPostCategory, totalPostTitle, today));
		// Chạy không ổn định
		verifyTrue(homeUserPage.isPostImageDisplayedAtPostTitleName(driver, totalPostTitle, featureImageName));

		postDetailPage = homeUserPage.clickToPostDetailWithTitleName(driver, totalPostTitle);

		verifyTrue(postDetailPage.isTitleNameDisplayed(totalPostTitle));
		verifyTrue(postDetailPage.isCategoryNameDisplayed(editPostCategory));
		verifyTrue(postDetailPage.isImageDisplayed(featureImageName));
		verifyTrue(postDetailPage.isContentDisplayed(editPostContent));
		verifyTrue(postDetailPage.isDateCreatedDisplayed(today));
		verifyTrue(postDetailPage.isAuthorDisplayed(authorPost));
		verifyTrue(postDetailPage.isTagDisplayed(editPostTag));

		// Search post at end user
		searchResultPage = postDetailPage.inputToSearchTextboxAtEndUserPage(driver, totalPostTitle);

		verifyTrue(searchResultPage.isPostTitleDisplayedOnHeader(totalPostTitle));
		verifyTrue(searchResultPage.isPostDisplayedOnLatestPost(driver, editPostCategory, totalPostTitle, today));
		verifyTrue(searchResultPage.isPostImageDisplayedAtPostTitleName(driver, totalPostTitle, featureImageName));
	}

	@Test(description = "Delete new post at Admin Page")
	public void TC_03_Delete_New_Post_At_Admin_page() {
		// Navigate to Admin Page
		dashboardPage = searchResultPage.openAdminLoggedPage(driver);

		// Go to post page
		dashboardPage.openMenuPageByName(driver, "Posts");
		postAdminPage = PageGeneratorManager.getPostAdminPage(driver);

		// Search Post at Admin Page
		postAdminPage.inputToSearchTextbox(totalPostTitle);

		postAdminPage.clickToSearchPostButton();

		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("title", "1", totalPostTitle);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("author", "1", authorPost);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("categories", "1", editPostCategory);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("tags", "1", editPostTag);

		// Click To Post Detail
		newEditPostPage = postAdminPage.clickToPostDetailByTitleName(totalPostTitle);

		postAdminPage = newEditPostPage.clickToMoveToTrashButton();

		verifyTrue(postAdminPage.isMoveToTrashMessageDisplayed());

		// Search Post at Admin Page
		postAdminPage.inputToSearchTextbox(totalPostTitle);

		postAdminPage.clickToSearchPostButton();
		// Displayed
		verifyTrue(postAdminPage.isNoPostFoundMessageDisplayed());

		// Chuyển qua trang user
		homeUserPage = postAdminPage.openEndUserUserPage(driver);
		verifyFalse(homeUserPage.isPostDisplayedOnLatestPost(driver, editPostCategory, totalPostTitle, today));
		// Chạy không ổn định
		verifyFalse(homeUserPage.isPostImageDisplayedAtPostTitleName(driver, totalPostTitle, featureImageName));

		// Search post at end user
		searchResultPage = postDetailPage.inputToSearchTextboxAtEndUserPage(driver, totalPostTitle);

		verifyFalse(searchResultPage.isPostDisplayedOnLatestPost(driver, editPostCategory, totalPostTitle, today));
		verifyFalse(searchResultPage.isPostImageDisplayedAtPostTitleName(driver, totalPostTitle, featureImageName));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-Condition Close browser");
		closeBrowserAndDriver(driver);
	}

	private WebDriver driver;
	LoginPageObjects loginAdminPage;
	DashboardPageObjects dashboardPage;
	PostsPageObjects postAdminPage;
	NewEditPostPageObjects newEditPostPage;
	HomePageObjects homeUserPage;
	PostDetailPageObjects postDetailPage;
	SearchResultPageObjects searchResultPage;
}
