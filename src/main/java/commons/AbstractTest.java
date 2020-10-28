package commons;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTest {
	protected final Log log;

	public AbstractTest() {
		log = LogFactory.getLog(getClass());
	}

	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();

	protected WebDriver getBrowserDriver(String browserName) {

		Browser browser = Browser.valueOf(browserName.toUpperCase());

		if (browser == browser.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			threadLocalDriver.set(new FirefoxDriver());
		} else if (browser == browser.CHROME) {
			WebDriverManager.chromedriver().setup();
			threadLocalDriver.set(new ChromeDriver());
		} else if (browser == browser.EDGE) {
			WebDriverManager.edgedriver().setup();
			threadLocalDriver.set(new EdgeDriver());
		} else if (browser == browser.IE) {
			WebDriverManager.iedriver().arch32().setup();
			threadLocalDriver.set(new InternetExplorerDriver());
		} else if (browser == browser.COCCOC) {
			WebDriverManager.chromedriver().browserVersion("81.0.4044.69").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("/Users/giangpk/Downloads/coccoc (1).dmg");
			threadLocalDriver.set(new ChromeDriver(options));
		} else if (browser == browser.SAFARI) {
			threadLocalDriver.set(new SafariDriver());
		} else if (browser == browser.CHROME_HEADLESS) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");// không có dấu nhân
			threadLocalDriver.set(new ChromeDriver(options));
		} else if (browser == browser.FIREFOX_HEADLESS) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");// không có dấu nhân
			threadLocalDriver.set(new FirefoxDriver(options));
		} else {
			throw new RuntimeException("Please input your browser");
		}
		return threadLocalDriver.get();
	}

	protected WebDriver getBrowserDriver(String browserName, String url) {
		Browser browser = Browser.valueOf(browserName.toUpperCase());

		if (browser == browser.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			setDriver(new FirefoxDriver());
		} else if (browser == browser.CHROME) {
			WebDriverManager.chromedriver().setup();
			setDriver(new ChromeDriver());
		} else if (browser == browser.EDGE) {
			WebDriverManager.edgedriver().setup();
			setDriver(new EdgeDriver());
		} else if (browser == browser.IE) {
			WebDriverManager.iedriver().arch32().setup();
			setDriver(new InternetExplorerDriver());
		} else if (browser == browser.COCCOC) {
			WebDriverManager.chromedriver().browserVersion("81.0.4044.69").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("/Users/giangpk/Downloads/coccoc (1).dmg");
			setDriver(new ChromeDriver(options));
		} else if (browser == browser.SAFARI) {
			setDriver(new SafariDriver());
		} else if (browser == browser.CHROME_HEADLESS) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");// không có dấu nhân
			setDriver(new ChromeDriver(options));
		} else if (browser == browser.FIREFOX_HEADLESS) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");// không có dấu nhân
			setDriver(new FirefoxDriver(options));
		} else {
			throw new RuntimeException("Please input your browser");
		}

		threadLocalDriver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		threadLocalDriver.get().manage().window().maximize();
		threadLocalDriver.get().get(url);
		return threadLocalDriver.get();
	}

	protected void removeDriver() {
		getDriver().quit();
		threadLocalDriver.remove();
	}

	private WebDriver getDriver() {
		return threadLocalDriver.get();
	}

	private void setDriver(WebDriver driver) {
		threadLocalDriver.set(driver);
	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	protected int randomData() {
		Random ran = new Random();
		return ran.nextInt(100000);
	}

	protected void closeBrowserAndDriver(WebDriver driver) {
		try {
			// get ra tên của OS và convert qua chữ thường
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			// Khai báo 1 biến command line để thực thi
			String cmd = "";
			if (driver != null) {
				driver.quit();
			}

			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
			} else if (driver.toString().toLowerCase().contains("internetexplorer")) {
				if (osName.toLowerCase().contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driver.toString().toLowerCase().contains("firefox")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill geckodriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				}
			}

			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();

			log.info("---------- QUIT BROWSER SUCCESS ----------");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	protected String getCurrentDay() {
		DateTime nowUTC = new DateTime(DateTimeZone.UTC);
		int day = nowUTC.getDayOfMonth();
		if (day < 10) {
			String dayValue = "0" + day;
			return dayValue;
		}
		return String.valueOf(day);
	}

	protected String getCurrentMonth() {
		DateTime now = new DateTime(DateTimeZone.UTC);
		int month = now.getMonthOfYear();
		if (month < 10) {
			String monthValue = "0" + month;
			return monthValue;
		}
		return String.valueOf(month);
	}

	protected String getCurrentYear() {
		DateTime now = new DateTime(DateTimeZone.UTC);
		return String.valueOf(now.getYear());
	}

	public String getLiveGuruToday() {
		return getCurrentDay() + "/" + getCurrentMonth() + "/" + getCurrentYear();
	}

	public String getWordpressToday() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return String.valueOf(formatter.format(date));
	}

}
