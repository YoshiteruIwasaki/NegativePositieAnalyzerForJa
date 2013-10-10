package beans;

import java.text.SimpleDateFormat;

import models.Item;
import models.Ranking;

public class RankingBean {

	private Item item;

	private Ranking ranking;

	private int countNeutralPercent;

	private int countPositivePercent;

	private int countNegativePercent;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Ranking getRanking() {
		return ranking;
	}

	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}

	public int getCountNeutralPercent() {
		return countNeutralPercent;
	}

	public void setCountNeutralPercent(int countNeutralPercent) {
		this.countNeutralPercent = countNeutralPercent;
	}

	public int getCountPositivePercent() {
		return countPositivePercent;
	}

	public void setCountPositivePercent(int countPositivePercent) {
		this.countPositivePercent = countPositivePercent;
	}

	public int getCountNegativePercent() {
		return countNegativePercent;
	}

	public void setCountNegativePercent(int countNegativePercent) {
		this.countNegativePercent = countNegativePercent;
	}

	public String getRankingDateString() {
		SimpleDateFormat sdf1 = new SimpleDateFormat(
				"yyyy'年'MM'月'dd'日'");
		return sdf1.format(ranking.date);
	}
}
