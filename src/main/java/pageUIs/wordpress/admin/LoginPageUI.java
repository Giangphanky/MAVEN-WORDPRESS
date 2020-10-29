package pageUIs.wordpress.admin;

public class LoginPageUI {
	public static final String EMAIL_TEXTBOX = "//input[@id='usernameOrEmail']";
	public static final String CONTINUE_BUTTON = "//button[text()='Continue']";
	public static final String LOGIN_BUTTON = "//button[text()='Log In']";
	public static final String PASSWORD_TEXTBOX = "//input[@id='password']";
	public static final String DYNAMIC_ERROR_MESSAGE = "//div[@class='form-input-validation is-error']/span[contains(text(),'%s')]";
}
