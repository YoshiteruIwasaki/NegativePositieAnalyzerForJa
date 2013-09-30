package services;

import models.Item;
import models.Log;
import beans.ItemBean;

public class ItemBeanService {

	public static ItemBean setItemBeanRanking(Item item, Long categoryId) {
		int countNeutral = TweetService.countNeutralTweetByItem(item);
		int countNegative = TweetService.countNegativeTweetByItem(item);
		int countPositive = TweetService.countPositiveTweetByItem(item);
		int totalCount = countNeutral + countNegative + countPositive;
		Log log = LogService.getLogTodayByItem(categoryId, item);
		int ranking = log == null ? 0 : log.ranking;
		ItemBean bean = new ItemBean();
		bean.setItem(item);
		bean.setCountNegative(countNegative);
		bean.setCountNeutral(countNeutral);
		bean.setCountPositive(countPositive);
		bean.setTotalCount(totalCount);
		bean.setRanking(ranking);
		return bean;
	}

	public static ItemBean setItemBean(Item item, Long categoryId) {
		int countNeutral = TweetService.countNeutralTweetByItem(item);
		int countNegative = TweetService.countNegativeTweetByItem(item);
		int countPositive = TweetService.countPositiveTweetByItem(item);
		int totalCount = countNeutral + countNegative + countPositive;
		ItemBean bean = new ItemBean();
		bean.setItem(item);
		bean.setCountNegative(countNegative);
		bean.setCountNeutral(countNeutral);
		bean.setCountPositive(countPositive);
		bean.setTotalCount(totalCount);
		bean.setRanking(0);
		return bean;
	}
}
