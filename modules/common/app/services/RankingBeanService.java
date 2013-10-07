package services;

import models.Item;
import models.Ranking;
import beans.RankingBean;

public class RankingBeanService {

	public static RankingBean setRankingBean(Ranking ranking) {
		Item item = ItemService.find.byId(ranking.itemId);
		RankingBean bean = new RankingBean();
		bean.setRanking(ranking);
		bean.setItem(item);
		int countNegativePercent = ranking.totalCount > 0 ? Math
				.round(ranking.countNegative * 100 / ranking.totalCount) : 0;
		bean.setCountNegativePercent(countNegativePercent);
		int countPositivePercent = ranking.totalCount > 0 ? Math
				.round(ranking.countPositive * 100 / ranking.totalCount) : 0;
		bean.setCountPositivePercent(countPositivePercent);
		int countNeutralPercent = ranking.totalCount > 0 ? 100
				- countNegativePercent - countPositivePercent : 0;
		bean.setCountNeutralPercent(countNeutralPercent);
		return bean;

	}

}
