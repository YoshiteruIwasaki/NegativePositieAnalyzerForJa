package utils;

import java.util.ArrayList;

import models.Category;
import models.Tweet;
import play.Logger;
import services.TweetService;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterUtils {

	/**
	 * Tweet接続設定
	 *
	 * @return
	 */
	private static Twitter getTwitterInstance(Category category) {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(category.consumerKey, category.consumerSecret);
		twitter4j.auth.AccessToken accessToken = new twitter4j.auth.AccessToken(
				category.accessToken, category.accessTokenSecret);
		twitter.setOAuthAccessToken(accessToken);
		return twitter;
	}

	/**
	 * Twitter検索
	 *
	 * @param keyword
	 * @return
	 */
	public static ArrayList<Tweet> getTweeterSearch(String keyword,
			Long sinceId, Category category) {
		ArrayList<Tweet> arrayList = new ArrayList<Tweet>();
		Twitter twitter = getTwitterInstance(category);
		try {
			Query query = new Query(keyword);
			if (sinceId > 0) {
				query.setSinceId(sinceId);
			}
			query.setLang("ja");
			query.setCount(ApplicationConfigUtils.TWEET_SEARCH_MAX_COUNT);
			QueryResult result;
			result = twitter.search(query);

			for (Status status : result.getTweets()) {
				if (!TweetService.hasTweetByTwitterId(status.getId())) {
					Tweet tweet = TweetService.saveTweetByStatus(status,
							keyword, category);
					if (tweet != null) {
						arrayList.add(tweet);
					}
				}
			}
		} catch (TwitterException e) {
			Logger.error("[TwitterUtils]", e);
		}
		return arrayList;
	}

}
