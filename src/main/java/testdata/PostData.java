package testdata;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PostData {
	static int fakeNumber = randomNumber();

	public static final String featureImageName = "img1.png";
	public static final String newPostTitle = "New_post_title" + fakeNumber;
	public static final String newPostContent = "New_post_content" + fakeNumber;
	public static final String newPostTags = "new_tag_" + fakeNumber;
	public static final String newPostCategory = "NEW LIVE CODING";
	public static final String authorPost = "Automation FC";
	public static final String today = getWordpressToday();
	public static final String editPostTitle = "Edit";
	public static final String totalPostTitle = newPostTitle + editPostTitle;
	public static final String editPostContent = "Edit_post_content" + fakeNumber;
	public static final String editPostTag = "Edit_post_tag" + fakeNumber;
	public static final String editPostCategory = "EDIT LIVE CODING";

	public static int randomNumber() {
		Random num = new Random();
		return num.nextInt(99999999) + 1;

	}

	public static String getWordpressToday() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return String.valueOf(formatter.format(date));
	}
}
