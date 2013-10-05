package services;

import java.text.DateFormat;

import models.Item;
import models.Tweet;
import beans.TweetBean;

import components.ISO8601DateFormat;

public class TweetBeanService {

	public static TweetBean setTweetBean(Item item, Tweet tweet) {
		TweetBean bean = new TweetBean();
		bean.setItem(item);
		bean.setTweet(tweet);
		DateFormat dateFormat = ISO8601DateFormat.getDateFormat();
		bean.setDatetime(dateFormat.format(tweet.createdAt));
		bean.setUrl("https://twitter.com/" + tweet.tweeterUserScreenName + "/status/"
				+ tweet.tweeterId.toString());
		return bean;
	}
}
