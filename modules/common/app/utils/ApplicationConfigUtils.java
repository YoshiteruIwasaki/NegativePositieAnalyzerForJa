package utils;

public class ApplicationConfigUtils {

	public static final int TWEET_SEARCH_MAX_COUNT = 200;

	public static final int MAX_PER_PAGE = 10;

	public static final String SITE_TITLE = "ネガポジ判定[negaposia]";

	public static final String SITE_TITLE_CONCAT = " | ";

	public static String getSiteFullTitle(String title) {
		String string = "";
		if (title != null && !"".equals(title)) {
			string = title + SITE_TITLE_CONCAT;
		}
		return string + SITE_TITLE;

	}
}
