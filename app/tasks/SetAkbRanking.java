package tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import models.Category;
import models.Item;
import models.Ranking;
import services.CategoryService;
import services.ItemService;
import services.RankingService;
import services.TweetService;
import utils.ScoreUtils;

public class SetAkbRanking {

	public static final Long CATEGORY_ID = 5L;

	public static void main() {

		HashMap<Ranking, Double> hashMap = new HashMap<Ranking, Double>();
		int rank = 0;

		Category category = CategoryService.find.byId(CATEGORY_ID);

		if (!RankingService.hasYesterdayRankingByCategory(category)) {
			List<Item> itemList = ItemService.getItemListByCategory(category);

			for (Item item : itemList) {
				int countNegative = TweetService
						.countNegativeTweetByItemLastDay(item);
				int countPositive = TweetService
						.countPositiveTweetByItemLastDay(item);
				int countNeutral = TweetService
						.countNeutralTweetByItemLastDay(item);
				Ranking ranking = RankingService.saveRanking(item, CATEGORY_ID,
						countPositive, countNegative, countNeutral);
				// スコアセット
				double score = ScoreUtils.getScore(ranking);
				Double double1 = new Double(score);
				hashMap.put(ranking, double1);
			}

			// スコアによる並べ替え
			List<Map.Entry<Ranking, Double>> arrayList = new ArrayList<Map.Entry<Ranking, Double>>(
					hashMap.entrySet());
			Collections.sort(arrayList,
					new Comparator<Map.Entry<Ranking, Double>>() {
						@Override
						public int compare(Entry<Ranking, Double> entry1,
								Entry<Ranking, Double> entry2) {
							return ((Double) entry2.getValue())
									.compareTo((Double) entry1.getValue());
						}
					});

			for (Entry<Ranking, Double> entry : arrayList) {
				rank++;
				Ranking ranking = entry.getKey();
				RankingService.setRank(ranking, rank);
			}
		}
	}
}
