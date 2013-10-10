package services;

import java.util.Date;
import java.util.List;

import com.avaje.ebean.Page;
import com.avaje.ebean.PagingList;

import models.Category;
import models.Item;
import models.Ranking;
import models.Tweet;
import play.db.ebean.Model.Finder;
import utils.ApplicationConfigUtils;

import components.DateFormat;

public class RankingService {

	public static Finder<Long, Ranking> find = new Finder<Long, Ranking>(
			Long.class, Ranking.class);

	/**
	 *
	 * @param item
	 * @return
	 */
	public static Boolean hasYesterdayRankingByCategory(Category category) {
		return find.where().eq("categoryId", category.categoryId)
				.eq("date", DateFormat.getLastDateStart()).findRowCount() > 0 ? true
				: false;
	}

	/**
	 *
	 * 昨日のランキング取得
	 *
	 * @param item
	 * @return
	 */
	public static List<Ranking> getYesterdayRankingListByCategory(
			Category category) {
		return find.where().eq("categoryId", category.categoryId)
				.eq("date", DateFormat.getLastDateStart()).order()
				.asc("ranking").findList();
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public static Ranking getRankingByRankingPreviousDay(Long itemId) {
		Date dayBeforeYesterday = DateFormat.getPreviousDateStart();
		List<Ranking> findList = find.where().eq("itemId", itemId)
				.eq("date", dayBeforeYesterday).findList();
		if (findList.size() > 0) {
			return findList.get(0);

		}
		return null;
	}

	/**
	 * データ登録
	 *
	 * @param countNeutral
	 * @param countNegative
	 * @param countPositive
	 *
	 * @param keyword
	 *
	 * @param node
	 * @return
	 */
	public static Ranking saveRanking(Item item, Long categoryId,
			int countPositive, int countNegative, int countNeutral) {
		Ranking ranking = new Ranking();
		ranking.date = DateFormat.getLastDateStart();
		ranking.itemId = item.itemId;
		ranking.categoryId = categoryId;
		ranking.countNegative = countNegative;
		ranking.countNeutral = countNeutral;
		ranking.countPositive = countPositive;
		ranking.totalCount = countPositive + countNegative + countNeutral;
		ranking.comparison = 0;
		ranking.ranking = 0;
		ranking.save();
		return ranking;

	}

	public static Ranking setRank(Ranking ranking, int rank) {
		ranking.ranking = rank;
		Ranking previousDayRanking = getRankingByRankingPreviousDay(ranking.itemId);
		int comparison = 0;
		if (previousDayRanking != null) {
			comparison = previousDayRanking.ranking - rank;
		}
		ranking.comparison = comparison;
		ranking.update();
		return ranking;
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public static PagingList<Ranking> getRankingCriteria(Item item) {
		return find.where().eq("itemId", item.itemId).orderBy().desc("date")
				.findPagingList(ApplicationConfigUtils.MAX_PER_PAGE);
	}

	/**
	 *
	 * @param item
	 * @return
	 */
	public static List<Ranking> getRankingResultList(Item item, Integer page) {
		PagingList<Ranking> pagingList = getRankingCriteria(item);
		Page<Ranking> currentPage = pagingList.getPage(page - 1);
		return currentPage.getList();
	}

	public static List<Ranking> getRankingListByItem(Item item) {
		return find.where().eq("itemId", item.itemId).orderBy().asc("date")
				.findList();
	}
	/**
	 *
	 * @param item
	 * @return
	 */
	public static Ranking getLatestRanking(Long itemId) {
		List<Ranking> findList =  find.where().eq("itemId", itemId)
				.eq("date", DateFormat.getLastDateStart()).findList();
		if (findList.size() > 0) {
			return findList.get(0);

		}
		return null;
	}
}
