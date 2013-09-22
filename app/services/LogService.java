package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import models.Category;
import models.Item;
import models.Log;
import play.db.ebean.Model.Finder;

public class LogService {

	public static Finder<Long, Log> find = new Finder<Long, Log>(Long.class,
			Log.class);

	/**
	 * 日付指定のGoogleTrend取得
	 *
	 * @param date
	 * @return
	 */
	public static List<Log> getLogByDate(String date, Long categoryId) {
		return find.where().ge("date", date).eq("categoryId", categoryId)
				.orderBy("ranking").findList();
	}

	/**
	 * 日付指定のGoogleTrend取得
	 *
	 * @param date
	 * @return
	 */
	public static Log getLogByDateAndItem(String date, Long categoryId,
			Item item) {
		List<Log> list = find.where().ge("date", date)
				.eq("categoryId", categoryId).eq("itemId", item.itemId)
				.setMaxRows(1).order().desc("logId").findList();
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 日付指定のGoogleTrend取得
	 *
	 * @param date
	 * @return
	 */
	public static Log getLogByDateAndRanking(String date, Long categoryId,
			int ranking) {
		List<Log> list = find.where().ge("date", date)
				.eq("categoryId", categoryId).eq("ranking", ranking)
				.setMaxRows(1).order().desc("logId").findList();
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 日付指定のGoogleTrend取得
	 *
	 * @param date
	 * @return
	 */
	public static List<Log> getLogToday(Long categoryId) {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormat.format(date);
		return getLogByDate(dateString, categoryId);
	}

	/**
	 * 日付指定のGoogleTrend取得
	 *
	 * @param date
	 * @return
	 */
	public static Log getLogTodayByItem(Long categoryId, Item item) {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormat.format(date);
		return getLogByDateAndItem(dateString, categoryId, item);
	}

	/**
	 * 日付指定のGoogleTrend取得
	 *
	 * @param date
	 * @return
	 */
	public static Log getLogTodayByRanking(Long categoryId, int ranking) {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormat.format(date);
		return getLogByDateAndRanking(dateString, categoryId, ranking);
	}

	public static void deleteList(List<Log> list) {
		for (Log log : list) {
			log.delete();
		}
	}

	public static Log saveLogByItem(Item item, Date date, int ranking,
			Category category) {
		Log log = new Log();
		log.date = date;
		log.itemId = item.itemId;
		log.ranking = ranking;
		log.categoryId = category.categoryId;
		log.save();

		return log;
	}

}
