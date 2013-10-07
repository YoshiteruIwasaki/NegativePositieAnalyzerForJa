package controllers.akb;

import java.util.ArrayList;
import java.util.List;

import components.DateFormat;

import models.Category;
import models.Item;
import models.Log;
import models.Ranking;
import play.mvc.Controller;
import play.mvc.Result;
import services.CategoryBeanService;
import services.CategoryService;
import services.ItemBeanService;
import services.ItemService;
import services.LogService;
import services.RankingBeanService;
import services.RankingService;
import beans.CategoryBean;
import beans.ItemBean;
import beans.RankingBean;

import utils.AkbApplicationConfigUtils;
import views.html.akb.index;

public class Application extends Controller {

	public static Result index() {
		String title = AkbApplicationConfigUtils.getSiteFullTitle(AkbApplicationConfigUtils.SITE_SUB_TITLE);

		Category category = CategoryService.find.byId(AkbApplicationConfigUtils.CATEGORY_ID);
		List<Ranking> list = RankingService.getYesterdayRankingListByCategory(category);
		ArrayList<RankingBean> arrayList = new ArrayList<RankingBean>();
		int maxTweetCount = 0;
		for(Ranking ranking : list){
			maxTweetCount = maxTweetCount > ranking.totalCount ? maxTweetCount : ranking.totalCount;
			RankingBean bean = RankingBeanService.setRankingBean(ranking);
			arrayList.add(bean);
		}


		String h1 = AkbApplicationConfigUtils.SITE_SUB_TITLE;
		String lastDate = DateFormat.getLastDateStartString();
		String description = AkbApplicationConfigUtils.SITE_DESCRIPTION;

		return ok(index.render(title, h1, lastDate, description, arrayList, maxTweetCount));
	}

}
