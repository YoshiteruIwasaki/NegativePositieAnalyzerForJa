package services;

import java.util.Date;

import models.Category;
import models.Tweet;
import beans.CategoryBean;

public class CategoryBeanService {

	public static CategoryBean setCategoryBean(Category category) {
		int totalCount = TweetService.getCacheCountByCategory(category);
		Tweet latestTweet = TweetService.getCacheLatestTweetByCategory(category);

		Date latestTweetDate = (latestTweet != null && latestTweet.createdAt != null) ? latestTweet.createdAt
				: null;
		CategoryBean bean = new CategoryBean();
		bean.setCategory(category);
		bean.setTotalCount(totalCount);
		bean.setLatestTweetDate(latestTweetDate);
		return bean;

	}
}
