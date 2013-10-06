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
		int countNegativePercent = Math.round(ranking.countNegative * 100
				/ ranking.totalCount);
		bean.setCountNegativePercent(countNegativePercent);
		int countPositivePercent = Math.round(ranking.countPositive * 100
				/ ranking.totalCount);
		bean.setCountPositivePercent(countPositivePercent);
		int countNeutralPercent = 100 - countNegativePercent
				- countPositivePercent;
		bean.setCountNeutralPercent(countNeutralPercent);
		return bean;

	}

}
