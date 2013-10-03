package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

import models.Item;

public class ItemBean {

	private int countNeutral;

	private int countNegative;

	private int countPositive;

	private int totalCount;

	private int ranking;

	private Date latestTweetDate;

	private Item item;

	public int getCountNeutral() {
		return countNeutral;
	}

	public void setCountNeutral(int countNeutral) {
		this.countNeutral = countNeutral;
	}

	public int getCountNegative() {
		return countNegative;
	}

	public void setCountNegative(int countNegative) {
		this.countNegative = countNegative;
	}

	public int getCountPositive() {
		return countPositive;
	}

	public void setCountPositive(int countPositive) {
		this.countPositive = countPositive;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public Date getLatestTweetDate() {
		return latestTweetDate;
	}

	public String getLatestTweetDateString() {
		SimpleDateFormat sdf1 = new SimpleDateFormat(
				"yyyy'年'MM'月'dd'日' HH:mm:ss");
		return sdf1.format(latestTweetDate);
	}

	public void setLatestTweetDate(Date latestTweetDate) {
		this.latestTweetDate = latestTweetDate;
	}

}
