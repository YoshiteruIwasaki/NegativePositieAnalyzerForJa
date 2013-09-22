package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Item;
import models.Tweet;
import play.mvc.Controller;
import play.mvc.Result;
import services.ItemBeanService;
import services.ItemService;
import services.TweetBeanService;
import services.TweetService;
import views.html.detail;
import beans.ItemBean;
import beans.TweetBean;

public class ItemDetail extends Controller {

	public static Result index(Long categoryId, Long itemId) {
		ArrayList<TweetBean> arrayList = new ArrayList<TweetBean>();
		Item item = ItemService.find.byId(itemId);
		if (item != null) {
			ItemBean bean = ItemBeanService.setItemBean(item, categoryId);
			List<Tweet> list = TweetService.getTweetByItem(item);
			for (Tweet tweet : list) {
				TweetBean tweetBean = TweetBeanService
						.setTweetBean(item, tweet);
				arrayList.add(tweetBean);

			}
			return ok(detail.render(item.title, bean, arrayList));
		}

		return notFound();
	}
}
