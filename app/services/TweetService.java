package services;

import java.util.List;

import com.avaje.ebean.Page;
import com.avaje.ebean.PagingList;

import models.Category;
import models.Item;
import models.Tweet;
import play.Logger;
import play.db.ebean.Model.Finder;
import rikyu.model.Sentence;
import twitter4j.Status;
import utils.ApplicationConfigUtils;

public class TweetService {

	public static Finder<Long, Tweet> find = new Finder<Long, Tweet>(
			Long.class, Tweet.class);

	/**
	 * ItemId指定の最大twitterId取得
	 *
	 * @param date
	 * @return
	 */
	public static Tweet getMaxTweetByItem(Item item) {
		try {
			List<Tweet> list = find.where().eq("itemId", item.itemId)
					.orderBy("tweeterId").setMaxRows(1).findList();
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			Logger.error("[TweetService]", e);
		}
		return null;
	}

	/**
	 *
	 *
	 * @param date
	 * @return
	 */
	public static boolean hasTweetByTwitterId(Long twitterId) {
		return find.where().eq("tweeterId", twitterId).findRowCount() > 0 ? true
				: false;
	}

	/**
	 * Tweetデータ登録
	 *
	 * @param keyword
	 *
	 * @param node
	 * @return
	 */
	public static Tweet saveTweetByStatus(Status status, String keyword,
			Category category) {
		try {
			Tweet tweet = new Tweet();
			tweet.searchWord = keyword;
			tweet.tweeterId = status.getId();
			tweet.createdAt = status.getCreatedAt();
			tweet.source = status.getSource();
			tweet.text = status.getText();
			tweet.tweeterUserId = status.getUser().getId();
			tweet.tweeterUsetName = status.getUser().getName();
			tweet.tweeterUserScreenName = status.getUser().getScreenName();
			tweet.tweeterUserUrl = status.getUser().getURL();
			tweet.tweeterUserImageUrl = status.getUser()
					.getProfileImageURLHttps();
			tweet.point = 0D;
			tweet.rawPoint = 0D;
			tweet.categoryId = category.categoryId;
			tweet.save();
			return tweet;
		} catch (Exception e) {
			Logger.error("[TweetService]", e);
		}
		return null;
	}

	public static Tweet updateItemTweet(Tweet tweet, Item item,
			Sentence sentence) {
		tweet.itemId = item.itemId;
		tweet.rawPoint = sentence.getPoint();
		tweet.point = sentence.getPoint() - item.point;
		tweet.update();
		return tweet;
	}

	/**
	 * ニュートラル
	 *
	 * @param item
	 * @return
	 */
	public static int countNeutralTweetByItem(Item item) {
		return find.where().eq("itemId", item.itemId).eq("point", 0D)
				.findRowCount();
	}

	/**
	 * ネガティブ
	 *
	 * @param item
	 * @return
	 */
	public static int countNegativeTweetByItem(Item item) {
		return find.where().eq("itemId", item.itemId).lt("point", 0D)
				.findRowCount();
	}

	/**
	 * ポジティブ
	 *
	 * @param item
	 * @return
	 */
	public static int countPositiveTweetByItem(Item item) {
		return find.where().eq("itemId", item.itemId).gt("point", 0D)
				.findRowCount();
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public static List<Tweet> getTweetByItem(Item item) {
		return find.where().eq("itemId", item.itemId).findList();
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public static PagingList<Tweet> getTweetResultPagingList(Item item) {
		return find.where().eq("itemId", item.itemId).orderBy().desc("createdAt")
				.findPagingList(ApplicationConfigUtils.MAX_PER_PAGE);
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public static List<Tweet> getTweetResultList(Item item, Integer page) {
		PagingList<Tweet> pagingList = getTweetResultPagingList(item);
		Page<Tweet> currentPage = pagingList.getPage(page - 1);
		return currentPage.getList();
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public static Integer getTweetResultCount(Item item, Integer page) {
		PagingList<Tweet> pagingList = getTweetResultPagingList(item);
		return pagingList.getTotalPageCount();
	}

}
