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
import testdata.PostData;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.AfterClass;

public class Admin_01_Create_View_Edit_Delete extends AbstractTest {

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
		log.info("Post Page Admin - Step 1: Go to Post Page");
		dashboardPage.openMenuPageByName(driver, "Posts");
		postAdminPage = PageGeneratorManager.getPostAdminPage(driver);

		log.info("Post Page Admin - Step 2: Click to Add Button");
		newEditPostPage = postAdminPage.clickToAddNewButton();

		log.info("Post Page Admin - Step 3: Input Title to Post");
		newEditPostPage.inputToPostTitleTextbox(PostData.newPostTitle);

		log.info("Post Page Admin - Step 4: Input content to Post");
		newEditPostPage.inputToPostContentTextbox(PostData.newPostContent);

		log.info("Post Page Admin - Step 5: Click to Tab");
		newEditPostPage.clickToPostTab();

		log.info("Post Page Admin - Step 6: Click to open Categories section");
		newEditPostPage.clickToOpenDetailMenuByText("Categories");

		log.info("Post Page Admin - Step 7: input new Categories");
		newEditPostPage.selectCategoryCheckbox(PostData.newPostCategory);

		log.info("Post Page Admin - Step 8: Click to close Categories section");
		newEditPostPage.clickToCloseDetailMenuByText("Categories");

		log.info("Post Page Admin - Step 9: Click to open Tags section");
		newEditPostPage.clickToOpenDetailMenuByText("Tags");

		log.info("Post Page Admin - Step 10: Input to Post tag");
		newEditPostPage.inputToTagTextbox(PostData.newPostTags);

		log.info("Post Page Admin - Step 11: Verify input tag success");
		verifyTrue(newEditPostPage.isTagInputSuccess(PostData.newPostTags));

		log.info("Post Page Admin - Step 12: Click to close Tags section");
		newEditPostPage.clickToCloseDetailMenuByText("Tags");

		log.info("Post Page Admin - Step 13: Click to open Featured image");
		newEditPostPage.clickToOpenDetailMenuByText("Featured image");

		log.info("Post Page Admin - Step 14: Click to setFeatured image");
		newEditPostPage.clickToSetFeatureImageLink();

		log.info("Post Page Admin - Step 15: Click to open Categories section");
		newEditPostPage.clickToUploadFileTab();

		log.info("Post Page Admin - Step 16: Upload file");
		newEditPostPage.upLoadMultipleFiles(driver, PostData.featureImageName);

		log.info("Post Page Admin - Step 17: Verify file upload success");
		verifyTrue(newEditPostPage.areFileUploadedDisplayed(driver, PostData.featureImageName));

		log.info("Post Page Admin - Step 18: Click to set feature image");
		newEditPostPage.clickToSetFeatureImageButton();

		log.info("Post Page Admin - Step 19: Verify Image Upload Success");
		//verifyTrue(newEditPostPage.isFeatureImageUploadSuccess(PostData.featureImageName));

		log.info("Post Page Admin - Step 20: Click to Publish button");
		newEditPostPage.clickToPublishButton();

		log.info("Post Page Admin - Step 21: Verify Publish button displayed");
		verifyTrue(newEditPostPage.isConfirmPublishMessageDisplayed());

		log.info("Post Page Admin - Step 22: Click to Double check Publish button");
		newEditPostPage.clickToDoubleCheckPublishButton();

		log.info("Post Page Admin - Step 23: Verify Publish message dispayed");
		verifyTrue(newEditPostPage.isMessagePostPublishedDisplayed("Post published"));

		log.info("Post Page Admin - Step 24: Open Post page");
		dashboardPage = newEditPostPage.openAdminLoggedPage(driver);
		dashboardPage.openMenuPageByName(driver, "Posts");
		postAdminPage = PageGeneratorManager.getPostAdminPage(driver);

		log.info("Post Page Admin - Step 25: Input to search textbox");
		postAdminPage.inputToSearchTextbox(PostData.newPostTitle);

		log.info("Post Page Admin - Step 26: Click to seach button");
		postAdminPage.clickToSearchPostButton();

		log.info("Post Page Admin - Step 27: Verify post data display on table");
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("title", "1", PostData.newPostTitle);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("author", "1", PostData.authorPost);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("categories", "1", PostData.newPostCategory);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("tags", "1", PostData.newPostTags);

		log.info("User Page - Step 28: Open User page");
		homeUserPage = postAdminPage.openEndUserUserPage(driver);

		log.info("User Page - Step 29: Verify post displayed");
		verifyTrue(homeUserPage.isPostDisplayedOnLatestPost(driver, PostData.newPostCategory, PostData.newPostTitle,
				PostData.today));

		log.info("User Page - Step 30: Verify Image displayed");
		verifyTrue(homeUserPage.isPostImageDisplayedAtPostTitleName(driver, PostData.newPostTitle,
				PostData.featureImageName));

		log.info("User Page - Step 31: Click to Detail page");
		postDetailPage = homeUserPage.clickToPostDetailWithTitleName(driver, PostData.newPostTitle);

		log.info("User Page - Step 32: Verify Infor of post displayed");
		verifyTrue(postDetailPage.isTitleNameDisplayed(PostData.newPostTitle));
		verifyTrue(postDetailPage.isCategoryNameDisplayed(PostData.newPostCategory));
		verifyTrue(postDetailPage.isImageDisplayed(PostData.featureImageName));
		verifyTrue(postDetailPage.isContentDisplayed(PostData.newPostContent));
		verifyTrue(postDetailPage.isDateCreatedDisplayed(PostData.today));
		verifyTrue(postDetailPage.isAuthorDisplayed(PostData.authorPost));
		verifyTrue(postDetailPage.isTagDisplayed(PostData.newPostTags));

		log.info("User Page - Step 33: Search new post");
		searchResultPage = postDetailPage.inputToSearchTextboxAtEndUserPage(driver, PostData.newPostTitle);

		log.info("User Page - Step 34: Verify post displayed");
		verifyTrue(searchResultPage.isPostTitleDisplayedOnHeader(PostData.newPostTitle));
		verifyTrue(searchResultPage.isPostDisplayedOnLatestPost(driver, PostData.newPostCategory, PostData.newPostTitle,
				PostData.today));
		verifyTrue(searchResultPage.isPostImageDisplayedAtPostTitleName(driver, PostData.newPostTitle,
				PostData.featureImageName));
	}

	/**
	 * 
	 */
	@Test(description = "Edit new post at Admin page")
	public void TC_02_Edit_New_Post_At_Admin_page() {
		log.info("Post Page Admin - Step 1: Go to Post Page");
		dashboardPage = searchResultPage.openAdminLoggedPage(driver);
		dashboardPage.openMenuPageByName(driver, "Posts");
		postAdminPage = PageGeneratorManager.getPostAdminPage(driver);

		log.info("Post Page Admin - Step 2: Input new post title to search textbox");
		postAdminPage.inputToSearchTextbox(PostData.newPostTitle);

		log.info("Post Page Admin - Step 3: Click to seach button");
		postAdminPage.clickToSearchPostButton();

		log.info("Post Page Admin - Step 4: Verify post data display on table");
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("title", "1", PostData.newPostTitle);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("author", "1", PostData.authorPost);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("categories", "1", PostData.newPostCategory);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("tags", "1", PostData.newPostTags);

		log.info("Post Page Admin - Step 5: Click to post detail");
		newEditPostPage = postAdminPage.clickToPostDetailByTitleName(PostData.newPostTitle);

		log.info("Post Page Admin - Step 6: Click to Tab");
		newEditPostPage.clickToPostTab();

		log.info("Post Page Admin - Step 7: Click to open Categories section");
		newEditPostPage.clickToOpenDetailMenuByText("Categories");

		log.info("Post Page Admin - Step 8: deselect new Categories");
		newEditPostPage.deSelectCategoryCheckbox(PostData.newPostCategory);

		log.info("Post Page Admin - Step 9: select new Categories");
		newEditPostPage.selectCategoryCheckbox(PostData.editPostCategory);

		log.info("Post Page Admin - Step 10: Click to close Categories section");
		newEditPostPage.clickToCloseDetailMenuByText("Categories");

		log.info("Post Page Admin - Step 11: Click to open Tags section");
		newEditPostPage.clickToOpenDetailMenuByText("Tags");

		log.info("Post Page Admin - Step 12: Click to delete tags");
		newEditPostPage.clickToDeleteTagButton(PostData.newPostTags);

		log.info("Post Page Admin - Step 13: Click to create new edit tag");
		newEditPostPage.inputToTagTextbox(PostData.editPostTag);

		log.info("Post Page Admin - Step 14: Verify edit tag is added success");
		verifyTrue(newEditPostPage.isTagInputSuccess(PostData.editPostTag));

		log.info("Post Page Admin - Step 15: Click to edit title");
		newEditPostPage.editToPostTitleTextbox(PostData.editPostTitle);

		log.info("Post Page Admin - Step 16: Click to edit content");
		newEditPostPage.editToPostContentTextbox(PostData.editPostContent);

		log.info("Post Page Admin - Step 17: Click to update button");
		newEditPostPage.clickToUpdateButton();

		log.info("Post Page Admin - Step 18: Verify updated message displayed");
		verifyTrue(newEditPostPage.isMessagePostPublishedDisplayed("Post updated"));

		log.info("Post Page Admin - Step 19: Verify Update button is clicked");
		newEditPostPage.checkIfUpdateButtonClickAgain();

		log.info("Post Page Admin - Step 20: Verify update button is disabled");
		verifyTrue(newEditPostPage.isUpdateButtonDisable());

		log.info("Post Page Admin - Step 21: Open Post page");
		dashboardPage = newEditPostPage.openAdminLoggedPage(driver);
		dashboardPage.openMenuPageByName(driver, "Posts");
		postAdminPage = PageGeneratorManager.getPostAdminPage(driver);

		log.info("Post Page Admin - Step 22: Input to search textbox");
		postAdminPage.inputToSearchTextbox(PostData.totalPostTitle);

		log.info("Post Page Admin - Step 23: Click to seach button");
		postAdminPage.clickToSearchPostButton();

		log.info("Post Page Admin - Step 24: Verify post data display on table");
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("title", "1", PostData.totalPostTitle);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("author", "1", PostData.authorPost);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("categories", "1", PostData.editPostCategory);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("tags", "1", PostData.editPostTag);

		log.info("User Page - Step 25: Open User page");
		homeUserPage = postAdminPage.openEndUserUserPage(driver);

		log.info("User Page - Step 26: Verify post displayed");
		verifyTrue(homeUserPage.isPostDisplayedOnLatestPost(driver, PostData.editPostCategory, PostData.totalPostTitle,
				PostData.today));

		log.info("User Page - Step 27: Verify Image displayed");
		verifyTrue(homeUserPage.isPostImageDisplayedAtPostTitleName(driver, PostData.totalPostTitle,
				PostData.featureImageName));

		log.info("User Page - Step 28: Click to Detail page");
		postDetailPage = homeUserPage.clickToPostDetailWithTitleName(driver, PostData.totalPostTitle);

		log.info("User Page - Step 29: Verify Infor of post displayed");
		verifyTrue(postDetailPage.isTitleNameDisplayed(PostData.totalPostTitle));
		verifyTrue(postDetailPage.isCategoryNameDisplayed(PostData.editPostCategory));
		verifyTrue(postDetailPage.isImageDisplayed(PostData.featureImageName));
		verifyTrue(postDetailPage.isContentDisplayed(PostData.editPostContent));
		verifyTrue(postDetailPage.isDateCreatedDisplayed(PostData.today));
		verifyTrue(postDetailPage.isAuthorDisplayed(PostData.authorPost));
		verifyTrue(postDetailPage.isTagDisplayed(PostData.editPostTag));

		log.info("User Page - Step 30: Search new post");
		searchResultPage = postDetailPage.inputToSearchTextboxAtEndUserPage(driver, PostData.totalPostTitle);

		log.info("User Page - Step 31: Verify post displayed");
		verifyTrue(searchResultPage.isPostTitleDisplayedOnHeader(PostData.totalPostTitle));
		verifyTrue(searchResultPage.isPostDisplayedOnLatestPost(driver, PostData.editPostCategory,
				PostData.totalPostTitle, PostData.today));
		verifyTrue(searchResultPage.isPostImageDisplayedAtPostTitleName(driver, PostData.totalPostTitle,
				PostData.featureImageName));
	}

	@Test(description = "Delete new post at Admin Page")
	public void TC_03_Delete_New_Post_At_Admin_page() {
		log.info("Post Page Admin - Step 1: Go to Post Page");
		dashboardPage = searchResultPage.openAdminLoggedPage(driver);
		dashboardPage.openMenuPageByName(driver, "Posts");
		postAdminPage = PageGeneratorManager.getPostAdminPage(driver);

		log.info("Post Page Admin - Step 2: Input edit post title to search textbox");
		postAdminPage.inputToSearchTextbox(PostData.totalPostTitle);

		log.info("Post Page Admin - Step 3: Click to seach button");
		postAdminPage.clickToSearchPostButton();

		log.info("Post Page Admin - Step 4: Verify post data display on table");
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("title", "1", PostData.totalPostTitle);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("author", "1", PostData.authorPost);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("categories", "1", PostData.editPostCategory);
		postAdminPage.isValueDisplayedAtColumnIDByRowNumber("tags", "1", PostData.editPostTag);

		log.info("Post Page Admin - Step 5: Click to post detail");
		newEditPostPage = postAdminPage.clickToPostDetailByTitleName(PostData.totalPostTitle);

		log.info("Post Page Admin - Step 6: Click to Move to Trash button");
		postAdminPage = newEditPostPage.clickToMoveToTrashButton();

		log.info("Post Page Admin - Step 7: Verify Move to Trash success");
		verifyTrue(postAdminPage.isMoveToTrashMessageDisplayed());

		log.info("Post Page Admin - Step 8: Input to search textbox");
		postAdminPage.inputToSearchTextbox(PostData.totalPostTitle);

		log.info("Post Page Admin - Step 9: Click to seach button");
		postAdminPage.clickToSearchPostButton();

		log.info("Post Page Admin - Step 10: Verify No Founded msg displayed");
		verifyTrue(postAdminPage.isNoPostFoundMessageDisplayed());

		log.info("User Page - Step 11: Open User page");
		homeUserPage = postAdminPage.openEndUserUserPage(driver);

		log.info("User Page - Step 12: Verify post is not displayed");
		verifyFalse(homeUserPage.isPostDisplayedOnLatestPost(driver, PostData.editPostCategory, PostData.totalPostTitle,
				PostData.today));

		log.info("User Page - Step 13: Verify image is not displayed");
		verifyFalse(homeUserPage.isPostImageDisplayedAtPostTitleName(driver, PostData.totalPostTitle,
				PostData.featureImageName));

		log.info("User Page - Step 14: Search new post");
		searchResultPage = postDetailPage.inputToSearchTextboxAtEndUserPage(driver, PostData.totalPostTitle);

		log.info("User Page - Step 15: Verify post is not displayed");
		verifyFalse(searchResultPage.isPostDisplayedOnLatestPost(driver, PostData.editPostCategory,
				PostData.totalPostTitle, PostData.today));
		verifyFalse(searchResultPage.isPostImageDisplayedAtPostTitleName(driver, PostData.totalPostTitle,
				PostData.featureImageName));
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
