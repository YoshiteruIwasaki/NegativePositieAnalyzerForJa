package utils;

import java.util.ArrayList;
import java.util.List;

import models.Category;
import models.Item;
import models.Tweet;
import rikyu.model.Sentence;
import services.TweetService;

public class EvaluationUtils {

	public static List<Tweet> getTweetList(List<Item> itemList,
			Category category) {
		ArrayList<Tweet> tweeterList = new ArrayList<Tweet>();
		if (itemList != null && itemList.size() > 0) {
			for (Item item : itemList) {
				Long sinceId = 0L;
				// 重複データ除外処理
				Tweet latestTweet = TweetService.getMaxTweetByItem(item);
				if (latestTweet != null) {
					sinceId = latestTweet.tweeterId;
				}
				ArrayList<Tweet> list = TwitterUtils.getTweeterSearch(
						item.title, sinceId, category);
				if (list != null && list.size() > 0) {
					for (Tweet tweet : list) {
						Sentence sentence = RikyuUtils.analyze(tweet.text);
						TweetService.updateItemTweet(tweet, item, sentence);
					}
					tweeterList.addAll(list);
				}
			}
		}
		return tweeterList;
	}
}
