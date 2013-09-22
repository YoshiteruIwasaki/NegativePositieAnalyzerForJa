package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AmazonUtils {
	public static String trimTitle(String title, int i) {
		String regex = "#" + i + ": ";
		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(title);
		title = m.replaceFirst("");
		return title;
	}
}
