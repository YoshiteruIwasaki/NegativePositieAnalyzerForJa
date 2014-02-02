package controllers.tokyo;

import java.util.ArrayList;
import java.util.List;

import components.DateFormat;

import models.Item;
import models.Ranking;
import models.Tweet;
import play.mvc.Controller;
import play.mvc.Result;
import services.ItemBeanService;
import services.ItemService;
import services.RankingBeanService;
import services.RankingService;
import services.TweetBeanService;
import services.TweetService;
import utils.TokyoApplicationConfigUtils;
import utils.ApplicationConfigUtils;
import beans.DateItemBean;
import beans.ItemBean;
import beans.RankingBean;
import beans.TweetBean;
import views.html.tokyo.detail;

public class Detail extends Controller {

	public static Result index(Long itemId, Integer page) {

		ArrayList<TweetBean> arrayList = new ArrayList<TweetBean>();
		ArrayList<RankingBean> rankingBeanList = new ArrayList<RankingBean>();

		Item item = ItemService.getCacheItem(itemId);
		if (item != null) {
			String  title = TokyoApplicationConfigUtils.getSiteFullTitle("[" + item.title + "] " + TokyoApplicationConfigUtils.SITE_SUB_TITLE);
			String  siteTitle = TokyoApplicationConfigUtils.getSiteFullTitle(TokyoApplicationConfigUtils.SITE_SUB_TITLE);

			ItemBean bean = ItemBeanService.setItemBean(item, TokyoApplicationConfigUtils.CATEGORY_ID);

			//ツイート一覧
			List<Tweet> list = TweetService.getTweetGroupResultList(item, page);
			for (Tweet tweet : list) {
				TweetBean tweetBean = TweetBeanService
						.setTweetBean(item, tweet);
				arrayList.add(tweetBean);
			}
			int count = TweetService.getTweetGroupResultCount(item, page);
			int countByItem = TweetService.getCountGroupByItem(item);
			List<DateItemBean> dateList = TweetService.getTweetResultListGroupByDate(item);

			//ランキング
			Ranking latestRanking = RankingService.getCacheLatestRanking(item.itemId);
			RankingBean latestRankingBean = new RankingBean();
			if(latestRanking != null){
				 latestRankingBean = RankingBeanService.setRankingBean(latestRanking);
			}
			List<Ranking> rankingList = RankingService.getCacheRankingListByItem(item);
			for (Ranking ranking : rankingList) {
				RankingBean rankingBean = RankingBeanService.setRankingBean(ranking);
				rankingBeanList.add(rankingBean);
			}
			String lastDate = DateFormat.getLastDateStartString();

			return ok(detail.render(title, siteTitle, bean, arrayList, page, count, countByItem, dateList, latestRankingBean,rankingBeanList, lastDate));
		}

		return notFound();
	}
}
