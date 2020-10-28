package commons;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindElements;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.wordpress.admin.DashboardPageObjects;
import pageObjects.wordpress.user.HomePageObjects;
import pageObjects.wordpress.user.PostDetailPageObjects;
import pageObjects.wordpress.user.SearchResultPageObjects;
import pageUIs.wordpress.admin.AbstractWordpressPageUI;
import pageUIs.wordpress.user.AbstractPageUI;
import pageUIs.wordpress.user.PostPageUserUI;

public abstract class AbstractPages {

	public AbstractPages OpenMenuPageByPageName(WebDriver driver, String pageName) {
		waitElementClickable(driver, AbstractWordpressPageUI.DYNAMIC_LINK, pageName);
		clickToElement(driver, AbstractWordpressPageUI.DYNAMIC_LINK, pageName);

		switch (pageName) {
		case "Posts":
			return PageGeneratorManager.getPostAdminPage(driver);
		case "Pages":
			return PageGeneratorManager.getPagesAdminPage(driver);
		case "Media":
			return PageGeneratorManager.getMediaAdminPage(driver);
		default:
			return PageGeneratorManager.getDashboardAdminPage(driver);
		}
	}

	public void openMenuPageByName(WebDriver driver, String pageName) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, pageName);
	}

	public void setImplicitWait(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public void openPageUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void waitAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void accecptAlert(WebDriver driver) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public String getAlertText(WebDriver driver) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		return alert.getText();
	}

	public void sendKeysToAlert(WebDriver driver, String text) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		alert.sendKeys(text);
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindow = driver.getWindowHandles();

		for (String runWindow : allWindow) {
			driver.switchTo().window(runWindow);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title))
				break;
		}
	}

	public void switchWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void closeAllWindowWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String runWindow : allWindows) {

			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public String castRestParameter(String xpathValues, String... values) {
		xpathValues = String.format(xpathValues, (Object[]) values);
		return xpathValues;
	}

	public WebElement find(WebDriver driver, String xpathValue) {
		return driver.findElement(byXpath(xpathValue));
	}

	public List<WebElement> finds(WebDriver driver, String xpathValue) {
		return driver.findElements(byXpath(xpathValue));
	}

	public By byXpath(String xpathValue) {
		return By.xpath(xpathValue);
	}

	public void clickToElement(WebDriver driver, String xpathValue) {
		find(driver, xpathValue).click();
	}

	public void clickToElement(WebDriver driver, String xpathValue, String... values) {
		find(driver, castRestParameter(xpathValue, values)).click();
	}

	public void sendKeysToElement(WebDriver driver, String xpathValue, String text) {
		element = find(driver, xpathValue);
		element.clear();
		element.sendKeys(text);
	}

	public void sendKeysToElement(WebDriver driver, String xpathValue, String text, String... values) {
		element = find(driver, castRestParameter(xpathValue, values));
		element.clear();
		element.sendKeys(text);
	}

	public void submitToElement(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		element.submit();
	}

	public void selectItemInDropDown(WebDriver driver, String xpathValue, String itemValue) {
		select = new Select(find(driver, xpathValue));
		select.selectByVisibleText(itemValue);
	}

	public String getSeltectedItemDropDown(WebDriver driver, String xpathValue) {
		select = new Select(find(driver, xpathValue));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String xpathValue) {
		select = new Select(find(driver, xpathValue));
		return select.isMultiple();
	}

	public void selectItemCustomDropdown(WebDriver driver, String parentLocator, String childSelectLocator,
			String expectedItem) {
		find(driver, parentLocator).click();
		sleepInSeconds(2);
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(childSelectLocator)));
		elements = driver.findElements(byXpath(childSelectLocator));
		for (WebElement webElement : elements) {
			if (webElement.getText().equals(expectedItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView();", webElement);
				sleepInSeconds(2);
				webElement.click();
				sleepInSeconds(2);
				break;
			}
		}

	}

	public String getElementAttributeValue(WebDriver driver, String xpathValue, String attributeName) {
		element = find(driver, xpathValue);
		return element.getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		return element.getText();
	}

	public String getElementText(WebDriver driver, String xpathValue, String... values) {
		element = find(driver, castRestParameter(xpathValue, values));
		return element.getText();
	}

	public int countElementNumber(WebDriver driver, String xpathValue) {
		elements = finds(driver, xpathValue);
		return elements.size();
	}

	public int countElementNumber(WebDriver driver, String xpathValue, String... values) {
		elements = finds(driver, castRestParameter(xpathValue, values));
		System.out.println("Size =" + elements.size());
		return elements.size();
	}

	public void checkToCheckBox(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void checkToCheckBox(WebDriver driver, String xpathValue, String ... values) {
		xpathValue = castRestParameter(xpathValue, values);
		element = find(driver, xpathValue);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void unCheckToCheckBox(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public void unCheckToCheckBox(WebDriver driver, String xpathValue, String ...values) {
		xpathValue = castRestParameter(xpathValue, values);
		element = find(driver, xpathValue);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathValue) {
		return find(driver, xpathValue).isDisplayed();
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathValue, String... values) {
		return find(driver, castRestParameter(xpathValue, values)).isDisplayed();
	}

	public boolean isElementEnabled(WebDriver driver, String xpathValue) {
		return find(driver, xpathValue).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String xpathValue) {
		return find(driver, xpathValue).isSelected();
	}

	public void switchToFrameOrIFrame(WebDriver driver, String xpathValue) {
		driver.switchTo().frame(find(driver, xpathValue));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, String xpathValue) {
		action = new Actions(driver);
		action.moveToElement(find(driver, xpathValue)).perform();
	}

	public void sendKeyBoardToElement(WebDriver driver, String xpathValue, Keys key) {
		action = new Actions(driver);
		action.sendKeys(find(driver, xpathValue), key);
	}
	
	public void sendKeyBoardToElementToClear(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		element.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
	}

	public void pressKeyBoard(WebDriver driver, Keys key) {
		action = new Actions(driver);
		action.keyDown(key).perform();
		action.keyUp(key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public void highlightElement(WebDriver driver, String xpathValue) {
		WebElement element = find(driver, xpathValue);
		String originalStyle = element.getAttribute("style");
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSeconds(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathValue) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", find(driver, xpathValue));
	}

	public void scrollToElement(WebDriver driver, String xpathValue) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", find(driver, xpathValue));
	}

	public void scrollToElement(WebDriver driver, String xpathValue, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",
				find(driver, castRestParameter(xpathValue, values)));
	}

	public void sendkeyToElementByJS(WebDriver driver, String xpathValue, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", find(driver, xpathValue));
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathValue, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", find(driver, xpathValue));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathValue) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 0", find(driver, xpathValue));

		if (status)
			return true;
		return false;
	}
	
	public boolean isImageLoaded(WebDriver driver, String xpathValue, String ...values) {
		xpathValue = castRestParameter(xpathValue, values);
		jsExecutor = (JavascriptExecutor) driver;
		sleepInSeconds(5);
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", find(driver, xpathValue));
		if (status)
			return true;
		return false;
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public void waitElementVisible(WebDriver driver, String xpathValue) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(xpathValue)));
	}

	public void waitElementVisible(WebDriver driver, String xpathValue, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		xpathValue = castRestParameter(xpathValue, values);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(xpathValue)));
	}
	
	public void waitElementInvisible(WebDriver driver, String xpathValue) {
		explicitWait = new WebDriverWait(driver, shortTimeOut);
		overrideGlobelTimeout(driver, shortTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(xpathValue)));
		overrideGlobelTimeout(driver, longTimeOut);
	}

	public void waitElementClickable(WebDriver driver, String xpathValue) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(xpathValue)));
	}

	public void waitElementClickable(WebDriver driver, String xpathValue, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		xpathValue = castRestParameter(xpathValue, values);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(xpathValue)));

	}

	public void waitElementPresence(WebDriver driver, String xpathValue) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(byXpath(xpathValue)));
	}

	public void waitToElementUndisplayed(WebDriver driver, String xpathValue) {
		explicitWait = new WebDriverWait(driver, shortTimeOut);
		overrideGlobelTimeout(driver, shortTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(xpathValue)));
		overrideGlobelTimeout(driver, longTimeOut);
	}

	public boolean isElementUndisplayed(WebDriver driver, String xpathValue) {
		overrideGlobelTimeout(driver, shortTimeOut);
		System.out.println("Start Time: " + new Date().toString());
		elements = finds(driver, xpathValue);
		overrideGlobelTimeout(driver, longTimeOut);
		System.out.println("End Time: " + new Date().toString());
		if (elements.size() == 0) {
			System.out.println("Element isnot in DOM");
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/displayed");
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			return false;
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String xpathValue, String... values) {
		overrideGlobelTimeout(driver, shortTimeOut);
		elements = finds(driver, castRestParameter(xpathValue, values));
		overrideGlobelTimeout(driver, longTimeOut);
		if (elements.size() == 0) {
			System.out.println("Element isnot in DOM");
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/displayed");
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			return false;
		}
	}

	public boolean isWindows() {
		return (osName.toLowerCase().indexOf("win") >= 0);
	}

	public boolean isMac() {
		return (osName.toLowerCase().indexOf("mac") >= 0);
	}

	public boolean isUnix() {
		return (osName.toLowerCase().indexOf("nix") >= 0 || osName.toLowerCase().indexOf("nux") >= 0
				|| osName.toLowerCase().indexOf("aix") > 0);
	}

	public boolean isSolaris() {
		return (osName.toLowerCase().indexOf("sunos") >= 0);
	}

	public String getDirectorySlash(String folderName) {
		if (isMac() || isUnix() || isSolaris()) {
			folderName = "/" + folderName + "/";
		} else {
			folderName = "\\" + folderName + "\\";
		}
		return folderName;
	}

	public void upLoadMultipleFiles(WebDriver driver, String... fileNames) {

		String filePath = System.getProperty("user.dir")+ "/src/test/resources/" + getDirectorySlash("uploadFiles");
		System.out.println("*************************");
		System.out.println(filePath);
		System.out.println("*************************");
		String fullFileName = "";

		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		sendKeysToElement(driver, AbstractPageUI.UP_LOAD_FILE_TYPE, fullFileName);
	}

	public boolean areFileUploadedDisplay(WebDriver driver, String... fileNames) {
		boolean status = false;
		int number = fileNames.length;

		waitElementVisible(driver, AbstractWordpressPageUI.EDIT_TEXT_AFTER_UPLOADED_LINK);
		sleepInSeconds(5);
		elements = finds(driver, AbstractWordpressPageUI.ALL_UPLOADED_IMAGE);

		List<String> imageValues = new ArrayList<String>();

		int i = 0;
		for (WebElement image : elements) {
			imageValues.add(image.getAttribute("src"));
			i++;
			if (i == number) {
				break;
			}
		}

		for (String fileName : fileNames) {
			String[] files = fileName.split("\\.");
			fileName = files[0].toLowerCase();

			for (int j = 0; j < imageValues.size(); j++) {
				if (!imageValues.get(j).contains(fileName)) {
					status = false;
					if (j == imageValues.size() - 1) {
						return status;
					}
				} else {
					status = true;
					break;
				}
			}
		}
		return status;
	}

	public boolean areFileUploadedDisplayed(WebDriver driver, String... fileNames) {
		boolean status = false;
		int number = fileNames.length;
		sleepInSeconds(5);
		waitElementVisible(driver, AbstractWordpressPageUI.EDIT_TEXT_AFTER_UPLOADED_LINK);
		sleepInSeconds(5);
		elements = finds(driver, AbstractWordpressPageUI.ALL_UPLOADED_IMAGE);

		List<String> imageValues = new ArrayList<String>();

		int i = 0;
		for (WebElement image : elements) {
			imageValues.add(image.getAttribute("aria-label"));
			i++;
			if (i == number) {
				break;
			}
		}

		for (String fileName : fileNames) {
			String[] files = fileName.split("\\.");
			fileName = files[0].toLowerCase();

			for (int j = 0; j < imageValues.size(); j++) {
				if (!imageValues.get(j).contains(fileName)) {
					status = false;
					if (j == imageValues.size() - 1) {
						return status;
					}
				} else {
					status = true;
					break;
				}
			}
		}
		return status;
	}

	public HomePageObjects openEndUserUserPage(WebDriver driver) {
		openPageUrl(driver, GlobalConstants.USER_WORDPRESS_URL);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public DashboardPageObjects openAdminLoggedPage(WebDriver driver) {
		openPageUrl(driver, GlobalConstants.ADMIN_WORDPRESS_URL);
		return PageGeneratorManager.getDashboardAdminPage(driver);
	}

	public SearchResultPageObjects inputToSearchTextboxAtEndUserPage(WebDriver driver, String title) {
		waitElementVisible(driver, AbstractPageUI.SEARCH_ICON);
		clickToElement(driver, AbstractPageUI.SEARCH_ICON);
		waitElementVisible(driver, AbstractPageUI.SEARCH_TEXTBOX);
		sendKeysToElement(driver, AbstractPageUI.SEARCH_TEXTBOX, title);
		waitElementVisible(driver, AbstractPageUI.SEARCH_BUTTON);
		clickToElement(driver, AbstractPageUI.SEARCH_BUTTON);
		return PageGeneratorManager.getUserSearchResultPage(driver);
	}

	public void overrideGlobelTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public boolean isPostDisplayedOnLatestPost(WebDriver driver, String category, String title, String dateCreated) {
		try {
			waitElementVisible(driver, AbstractPageUI.DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE, category, title, dateCreated);
			return isElementDisplayed(driver, AbstractPageUI.DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE, category, title,
					dateCreated);
		} catch (Exception e) {
			return false;
		}
		
	}

	public boolean isPostImageDisplayedAtPostTitleName(WebDriver driver, String title, String imgPath) {
		try {
			String[] filePath = imgPath.split("\\.");
			waitElementVisible(driver, AbstractPageUI.DYNAMIC_IMAGE_POST_WITH_TITLE_IMAGE_NAME, title, filePath[0]);
			return isElementDisplayed(driver, AbstractPageUI.DYNAMIC_IMAGE_POST_WITH_TITLE_IMAGE_NAME, title, filePath[0]) 
					&& isImageLoaded(driver, AbstractPageUI.DYNAMIC_IMAGE_POST_WITH_TITLE_IMAGE_NAME, title, filePath[0]) ;
		} catch (Exception e) {
			return false;
		}
		
	}

	public PostDetailPageObjects clickToPostDetailWithTitleName(WebDriver driver, String title) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_POST_TITLE, title);
		clickToElement(driver, AbstractPageUI.DYNAMIC_POST_TITLE, title);
		return PageGeneratorManager.getUserPostDetailPage(driver);
	}

	public void sleepInSeconds(long time) {
		try {
			Thread.sleep(1000 * time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private long longTimeOut = 30;
	private long shortTimeOut = 10;
	private Select select;
	private Alert alert;
	private WebElement element;
	private List<WebElement> elements;
	private Actions action;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
	private String osName = System.getProperty("os.name");
}
