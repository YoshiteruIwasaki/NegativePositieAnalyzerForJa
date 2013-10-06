package controllers.akb;

import java.util.ArrayList;
import java.util.List;

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
		for(Ranking ranking : list){
			RankingBean bean = RankingBeanService.setRankingBean(ranking);
			arrayList.add(bean);
		}

		return ok(index.render(title, arrayList));
	}

}
