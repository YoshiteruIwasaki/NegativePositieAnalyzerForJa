package controllers.akb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import models.Category;
import models.Item;
import models.Ranking;
import play.mvc.Controller;
import play.mvc.Result;
import services.CategoryService;
import services.ItemService;
import services.RankingBeanService;
import services.RankingService;
import utils.AkbApplicationConfigUtils;
import beans.RankingBean;
import views.html.akb.index;

import components.DateFormat;

public class Application extends Controller {

	public static Result index() {
		String title = AkbApplicationConfigUtils
				.getSiteFullTitle(AkbApplicationConfigUtils.SITE_SUB_TITLE);

		Category category = CategoryService.find
				.byId(AkbApplicationConfigUtils.CATEGORY_ID);
		List<Ranking> list = RankingService
				.getYesterdayRankingListByCategory(category);
		ArrayList<RankingBean> arrayList = new ArrayList<RankingBean>();
		int maxTweetCount = 0;
		for (Ranking ranking : list) {
			maxTweetCount = maxTweetCount > ranking.totalCount ? maxTweetCount
					: ranking.totalCount;
			RankingBean bean = RankingBeanService.setRankingBean(ranking);
			arrayList.add(bean);
		}

		List<Item> itemList = ItemService.getItemListByCategory(category);

		List<Ranking> rankingList = RankingService
				.getRankingListByCategory(category);
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

		String h1 = AkbApplicationConfigUtils.SITE_SUB_TITLE;
		String lastDate = DateFormat.getLastDateStartString();
		String description = AkbApplicationConfigUtils.SITE_DESCRIPTION;

		return ok(index.render(title, h1, lastDate, description, arrayList,
				maxTweetCount, itemList, hashMap));
	}

}
