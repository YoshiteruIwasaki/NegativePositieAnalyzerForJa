package services;

import java.util.Date;

import models.Item;
import models.Log;
import models.Tweet;
import beans.ItemBean;

public class ItemBeanService {

	public static ItemBean setItemBeanCore(Item item, Long categoryId) {
		int countNeutral = TweetService.countNeutralTweetByItem(item);
		int countNegative = TweetService.countNegativeTweetByItem(item);
		int countPositive = TweetService.countPositiveTweetByItem(item);
		int totalCount = countNeutral + countNegative + countPositive;
		Tweet latestTweet = TweetService.getLatestTweetByItem(item);
		Date latestTweetDate = (latestTweet != null && latestTweet.createdAt != null) ? latestTweet.createdAt
				: null;
		ItemBean bean = new ItemBean();
		bean.setItem(item);
		bean.setCountNegative(countNegative);
		bean.setCountNeutral(countNeutral);
		bean.setCountPositive(countPositive);
		bean.setTotalCount(totalCount);
		bean.setLatestTweetDate(latestTweetDate);
		return bean;

	}

	public static ItemBean setItemBeanRanking(Item item, Long categoryId) {
		ItemBean bean = setItemBeanCore(item, categoryId);
		Log log = LogService.getLogTodayByItem(categoryId, item);
		int ranking = log == null ? 0 : log.ranking;
		bean.setRanking(ranking);
		return bean;
	}

	public static ItemBean setItemBean(Item item, Long categoryId) {
		ItemBean bean = setItemBeanCore(item, categoryId);
		bean.setRanking(0);
		return bean;
	}
}
