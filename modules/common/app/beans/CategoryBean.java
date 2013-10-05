package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

import models.Category;

public class CategoryBean {

	private int totalCount;

	private Date latestTweetDate;

	private Category category;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
