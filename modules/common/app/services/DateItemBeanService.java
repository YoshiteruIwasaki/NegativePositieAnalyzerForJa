package services;

import java.util.Date;

import models.Item;
import beans.DateItemBean;

public class DateItemBeanService extends ItemBeanService {

	public static DateItemBean setDateItemBean(Item item, int countNeutral,
			int countNegative, int countPositive, Date date) {
		int totalCount = countNeutral + countNegative + countPositive;
		DateItemBean bean = new DateItemBean();
		bean.setItem(item);
		bean.setCountNegative(countNegative);
		bean.setCountNeutral(countNeutral);
		bean.setCountPositive(countPositive);
		bean.setTotalCount(totalCount);
		bean.setRanking(0);
		bean.setDate(date);
		return bean;
	}
}
