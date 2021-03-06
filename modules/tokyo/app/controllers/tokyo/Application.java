package controllers.tokyo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import cache.CacheService;

import models.Category;
import models.Item;
import models.Ranking;
import play.cache.CachedAction;
import play.cache.Cached;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import services.CategoryService;
import services.ItemService;
import services.RankingBeanService;
import services.RankingService;
import utils.TokyoApplicationConfigUtils;
import beans.RankingBean;
import views.html.tokyo.index;

import components.DateFormat;

public class Application extends Controller {
	public static Result index() {
		String title = TokyoApplicationConfigUtils
				.getSiteFullTitle(TokyoApplicationConfigUtils.SITE_SUB_TITLE);

		Category category =
				CategoryService.getCacheCateory(TokyoApplicationConfigUtils.CATEGORY_ID);
		List<Ranking> list = RankingService
				.getCacheYesterdayRankingListByCategory(category);
		ArrayList<RankingBean> arrayList = new ArrayList<RankingBean>();
		int maxTweetCount = 0;
		for (Ranking ranking : list) {
			maxTweetCount = maxTweetCount > ranking.totalCount ? maxTweetCount
					: ranking.totalCount;
			RankingBean bean = RankingBeanService.setRankingBean(ranking);
			arrayList.add(bean);
		}

		List<Item> itemList = ItemService.getCacheItemListByCategory(category);

		List<Ranking> rankingList = RankingService
				.getCacheRankingListByCategory(category);
		LinkedHashMap<String, LinkedHashMap<Long, Integer>> hashMap = new LinkedHashMap<String, LinkedHashMap<Long, Integer>>();

		for (Ranking ranking : rankingList) {
			LinkedHashMap<Long, Integer> itemRankingMap = new LinkedHashMap<Long, Integer>();
			if (hashMap.containsKey(DateFormat
					.getRankingDateString(ranking.date))) {
				itemRankingMap = hashMap.get(DateFormat
						.getRankingDateString(ranking.date));
			}
			itemRankingMap.put(ranking.itemId, ranking.ranking);
			hashMap.put(DateFormat.getRankingDateString(ranking.date),
					itemRankingMap);
		}

		String h1 = TokyoApplicationConfigUtils.SITE_SUB_TITLE;
		String lastDate = DateFormat.getLastDateStartString();
		String description = TokyoApplicationConfigUtils.SITE_DESCRIPTION;

		return ok(index.render(title, h1, lastDate, description, arrayList,
				maxTweetCount, itemList, hashMap));
	}

}
