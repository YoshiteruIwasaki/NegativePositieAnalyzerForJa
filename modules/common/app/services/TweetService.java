package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Category;
import models.Item;
import models.Tweet;
import play.Logger;
import play.db.ebean.Model.Finder;
import rikyu.model.Sentence;
import twitter4j.Status;
import utils.ApplicationConfigUtils;
import beans.DateItemBean;
import cache.CacheService;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Page;
import com.avaje.ebean.PagingList;
import com.avaje.ebean.Query;
import com.avaje.ebean.SqlRow;
import components.DateFormat;

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
	public static int getCountByCategory(Category category) {
		return find.where().eq("categoryId", category.categoryId)
				.findRowCount();
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public static Tweet getLatestTweetByCategory(Category category) {
		List<Tweet> list = find.where().eq("categoryId", category.categoryId)
				.orderBy().desc("tweetId").setMaxRows(1).findList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public static int getCountByItem(Item item) {
		return find.where().eq("itemId", item.itemId).findRowCount();
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public static Tweet getLatestTweetByItem(Item item) {
		List<Tweet> list = find.where().eq("itemId", item.itemId).orderBy()
				.desc("tweetId").setMaxRows(1).findList();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
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
	public static List<Tweet> getTweetByItemLastDay(Item item) {
		Date yesterday = DateFormat.getLastDateStart();
		Date today = DateFormat.getTodayStart();
		return find.where().eq("itemId", item.itemId)
				.between("createdAt", yesterday, today).findList();
	}

	/**
	 * ニュートラル
	 *
	 * @param item
	 * @return
	 */
	public static int countNeutralTweetByItemLastDay(Item item) {
		Date yesterday = DateFormat.getLastDateStart();
		Date today = DateFormat.getTodayStart();
		return find.where().eq("itemId", item.itemId).eq("point", 0D)
				.between("createdAt", yesterday, today).findRowCount();
	}

	/**
	 * ネガティブ
	 *
	 * @param item
	 * @return
	 */
	public static int countNegativeTweetByItemLastDay(Item item) {
		Date yesterday = DateFormat.getLastDateStart();
		Date today = DateFormat.getTodayStart();
		return find.where().eq("itemId", item.itemId).lt("point", 0D)
				.between("createdAt", yesterday, today).findRowCount();
	}

	/**
	 * ポジティブ
	 *
	 * @param item
	 * @return
	 */
	public static int countPositiveTweetByItemLastDay(Item item) {
		Date yesterday = DateFormat.getLastDateStart();
		Date today = DateFormat.getTodayStart();
		return find.where().eq("itemId", item.itemId).gt("point", 0D)
				.between("createdAt", yesterday, today).findRowCount();
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public static PagingList<Tweet> getTweetCriteria(Item item) {
		return find.where().eq("itemId", item.itemId).orderBy()
				.desc("createdAt")
				.findPagingList(ApplicationConfigUtils.MAX_PER_PAGE);
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public static List<Tweet> getTweetResultList(Item item, Integer page) {
		PagingList<Tweet> pagingList = getTweetCriteria(item);
		Page<Tweet> currentPage = pagingList.getPage(page - 1);
		return currentPage.getList();
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public static Integer getTweetResultCount(Item item, Integer page) {
		PagingList<Tweet> pagingList = getTweetCriteria(item);
		return pagingList.getTotalPageCount();
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public static List<DateItemBean> getTweetResultListGroupByDate(Item item) {

		String sql = " SELECT created_at,"
				+ " COUNT( CASE WHEN POINT = 0 THEN 1 ELSE NULL END ) AS neutral,"
				+ " COUNT( CASE WHEN POINT > 0 THEN 1 ELSE NULL END ) AS positive,"
				+ " COUNT( CASE WHEN POINT < 0 THEN 1 ELSE NULL END ) AS negative"
				+ " FROM tweet" + " WHERE item_id = :item_id"
				+ " GROUP BY DATE_FORMAT( created_at,  '%Y%m%d' )"
				+ " ORDER BY created_at ASC";

		List<SqlRow> sqlRows = Ebean.createSqlQuery(sql)
				.setParameter("item_id", item.itemId).findList();

		List<DateItemBean> results = new ArrayList<DateItemBean>();
		for (SqlRow row : sqlRows) {
			Date date = row.getDate("created_at");
			Integer countNeutral = row.getInteger("neutral");
			Integer countPositive = row.getInteger("positive");
			Integer countNegative = row.getInteger("negative");
			DateItemBean bean = DateItemBeanService.setDateItemBean(item,
					countNeutral, countNegative, countPositive, date);
			results.add(bean);
		}
		return results;

	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public static int getCountGroupByItem(Item item) {
		String sql = "SELECT count(*) AS count FROM (SELECT count(*) FROM tweet WHERE item_id = :item_id"
				+ " GROUP BY text" + " ORDER BY created_at DESC) AS temp";
		List<SqlRow> sqlRows = Ebean.createSqlQuery(sql)
				.setParameter("item_id", item.itemId).findList();

		Integer count = 0;
		for (SqlRow row : sqlRows) {
			count = row.getInteger("count");
			break;
		}
		return count;
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public static PagingList<Tweet> getTweetGroupCriteria(Item item) {
		String sql = "find tweet WHERE item_id = :item_id" + " GROUP BY text"
				+ " ORDER BY created_at DESC";
		Query<Tweet> query = Ebean.createQuery(Tweet.class, sql);
		query.setParameter("item_id", item.itemId);
		return query.findPagingList(ApplicationConfigUtils.MAX_PER_PAGE);
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public static List<Tweet> getTweetGroupResultList(Item item, Integer page) {
		PagingList<Tweet> pagingList = getTweetGroupCriteria(item);
		Page<Tweet> currentPage = pagingList.getPage(page - 1);
		return currentPage.getList();
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public static Integer getTweetGroupResultCount(Item item, Integer page) {
		int count = getCountGroupByItem(item);
		return count > 0 ? (int) Math.ceil(count
				/ ApplicationConfigUtils.MAX_PER_PAGE) + 2 : 0;
	}

	@SuppressWarnings("unchecked")
	public static List<DateItemBean> getCacheTweetResultListGroupByDate(
			Item item) {
		String[] keys = { String.valueOf(item.itemId) };
		Class<?>[] param = new Class[] { Item.class };
		Object[] arguments = { item };
		return (List<DateItemBean>) CacheService.getObject(TweetService.class,
				CacheService.KeyType.LIST, keys,
				"getTweetResultListGroupByDate", param, arguments);
	}

	@SuppressWarnings("unchecked")
	public static List<Tweet> getCacheTweetGroupResultList(Item item,
			Integer page) {
		String[] keys = { String.valueOf(item.itemId), String.valueOf(page) };
		Class<?>[] param = new Class[] { Item.class, Integer.class };
		Object[] arguments = { item, page };
		return (List<Tweet>) CacheService.getObject(TweetService.class,
				CacheService.KeyType.LIST, keys, "getTweetGroupResultList",
				param, arguments);
	}

	@SuppressWarnings("unchecked")
	public static List<Tweet> getCacheTweetResultList(Item item, Integer page) {
		String[] keys = { String.valueOf(item.itemId), String.valueOf(page) };
		Class<?>[] param = new Class[] { Item.class, Integer.class };
		Object[] arguments = { item, page };
		return (List<Tweet>) CacheService.getObject(TweetService.class,
				CacheService.KeyType.LIST, keys, "getTweetResultList", param,
				arguments);
	}

	public static Integer getCacheTweetGroupResultCount(Item item, Integer page) {
		String[] keys = { String.valueOf(item.itemId) };
		Class<?>[] param = new Class[] { Item.class, Integer.class };
		Object[] arguments = { item, page };
		return (Integer) CacheService.getObject(TweetService.class,
				CacheService.KeyType.COUNT, keys, "getTweetGroupResultCount",
				param, arguments);
	}

	public static Integer getCacheTweetResultCount(Item item, Integer page) {
		String[] keys = { String.valueOf(item.itemId) };
		Class<?>[] param = new Class[] { Item.class, Integer.class };
		Object[] arguments = { item, page };
		return (Integer) CacheService.getObject(TweetService.class,
				CacheService.KeyType.COUNT, keys, "getTweetResultCount", param,
				arguments);
	}

	public static Integer getCacheCountGroupByItem(Item item) {
		String[] keys = { String.valueOf(item.itemId) };
		Class<?>[] param = new Class[] { Item.class };
		Object[] arguments = { item };
		return (Integer) CacheService.getObject(TweetService.class,
				CacheService.KeyType.COUNT, keys, "getCountGroupByItem", param,
				arguments);
	}

	public static int getCacheCountByItem(Item item) {
		String[] keys = { String.valueOf(item.itemId) };
		Class<?>[] param = new Class[] { Item.class };
		Object[] arguments = { item };
		return (Integer) CacheService.getObject(TweetService.class,
				CacheService.KeyType.COUNT, keys, "getCountByItem", param,
				arguments);
	}

	public static int getCacheCountByCategory(Category category) {
		String[] keys = { String.valueOf(category.categoryId) };
		Class<?>[] param = new Class[] { Category.class };
		Object[] arguments = { category };
		return (Integer) CacheService.getObject(TweetService.class,
				CacheService.KeyType.COUNT, keys, "getCountByCategory", param,
				arguments);
	}

	public static Tweet getCacheLatestTweetByCategory(Category category) {
		String[] keys = { String.valueOf(category.categoryId) };
		Class<?>[] param = new Class[] { Category.class };
		Object[] arguments = { category };
		return (Tweet) CacheService.getObject(TweetService.class,
				CacheService.KeyType.DETAIL, keys, "getLatestTweetByCategory",
				param, arguments);
	}

	public static Tweet getCacheLatestTweetByItem(Item item) {
		String[] keys = { String.valueOf(item.itemId) };
		Class<?>[] param = new Class[] { Item.class };
		Object[] arguments = { item };
		return (Tweet) CacheService.getObject(TweetService.class,
				CacheService.KeyType.DETAIL, keys, "getLatestTweetByItem",
				param, arguments);
	}

	public static int countCacheNeutralTweetByItem(Item item) {
		String[] keys = { String.valueOf(item.itemId) };
		Class<?>[] param = new Class[] { Item.class };
		Object[] arguments = { item };
		return (Integer) CacheService.getObject(TweetService.class,
				CacheService.KeyType.COUNT, keys, "countNeutralTweetByItem",
				param, arguments);
	}

	public static int countCacheNegativeTweetByItem(Item item) {
		String[] keys = { String.valueOf(item.itemId) };
		Class<?>[] param = new Class[] { Item.class };
		Object[] arguments = { item };
		return (Integer) CacheService.getObject(TweetService.class,
				CacheService.KeyType.COUNT, keys, "countNegativeTweetByItem",
				param, arguments);
	}

	public static int countCachePositiveTweetByItem(Item item) {
		String[] keys = { String.valueOf(item.itemId) };
		Class<?>[] param = new Class[] { Item.class };
		Object[] arguments = { item };
		return (Integer) CacheService.getObject(TweetService.class,
				CacheService.KeyType.COUNT, keys, "countPositiveTweetByItem",
				param, arguments);
	}
}
