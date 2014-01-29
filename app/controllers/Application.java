package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Category;
import models.Item;
import models.Log;
import play.mvc.Controller;
import play.mvc.Result;
import services.CategoryBeanService;
import services.CategoryService;
import services.ItemBeanService;
import services.ItemService;
import services.LogService;
import beans.CategoryBean;
import beans.ItemBean;

import views.html.index;
import views.html.category;

public class Application extends Controller {

	public static Result index() {

		List<Category> categoryList = CategoryService.getCategoryList();
		ArrayList<CategoryBean> arrayList = new ArrayList<CategoryBean>();
		for (Category category : categoryList) {
			CategoryBean bean = CategoryBeanService.setCategoryBean(category);
			arrayList.add(bean);
		}

		return ok(index.render("ネガポジ判定", arrayList));
	}

	public static Result detail(Long categoryId) {

		Category categoryBean = CategoryService.find.byId(categoryId);
		if (categoryBean == null){
			return notFound();
		}

		ArrayList<ItemBean> arrayList = new ArrayList<ItemBean>();
		if (categoryBean != null && categoryBean.link != null
				&& !"".equals(categoryBean.link)) {
			List<Log> logList = LogService.getLogToday(categoryId);

			List<Item> list = ItemService.getItemList(logList);
			if (list != null && list.size() > 0) {
				for (Item item : list) {
					ItemBean bean = ItemBeanService.setItemBeanRanking(item,
							categoryId);
					arrayList.add(bean);
				}

			}
		} else {
			List<Item> list = ItemService.getItemListByCategory(categoryBean);
			if (list != null && list.size() > 0) {
				for (Item item : list) {
					ItemBean bean = ItemBeanService.setItemBean(item,
							categoryId);
					arrayList.add(bean);
				}

			}

		}
		return ok(category.render(categoryBean.title + "ネガポジ判定", categoryBean,
				arrayList));
	}

}
