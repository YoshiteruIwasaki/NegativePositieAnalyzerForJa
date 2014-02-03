package controllers.akb;

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
import utils.AkbApplicationConfigUtils;
import utils.ApplicationConfigUtils;
import beans.DateItemBean;
import beans.ItemBean;
import beans.RankingBean;
import beans.TweetBean;
import views.html.akb.detail;

public class Detail extends Controller {

	public static Result index(Long itemId, Integer page) {

		ArrayList<TweetBean> arrayList = new ArrayList<TweetBean>();
		ArrayList<RankingBean> rankingBeanList = new ArrayList<RankingBean>();

		Item item = ItemService.getCacheItem(itemId);
		if (item != null) {
			String  title = AkbApplicationConfigUtils.getSiteFullTitle("[" + item.title + "] " + AkbApplicationConfigUtils.SITE_SUB_TITLE);
			String  siteTitle = AkbApplicationConfigUtils.getSiteFullTitle(AkbApplicationConfigUtils.SITE_SUB_TITLE);

			ItemBean bean = ItemBeanService.setItemBean(item, AkbApplicationConfigUtils.CATEGORY_ID);

			//ツイート一覧
			List<Tweet> list = TweetService.getCacheTweetGroupResultList(item, page);
			for (Tweet tweet : list) {
				TweetBean tweetBean = TweetBeanService
						.setTweetBean(item, tweet);
				arrayList.add(tweetBean);
			}
			int count = TweetService.getCacheTweetGroupResultCount(item, page);
			int countByItem = TweetService.getCacheCountGroupByItem(item);
			List<DateItemBean> dateList = TweetService.getCacheTweetResultListGroupByDate(item);

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
