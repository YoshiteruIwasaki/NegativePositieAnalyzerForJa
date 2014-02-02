package tasks;

import java.util.ArrayList;
import java.util.List;

import models.Category;
import models.Item;
import models.Log;
import services.CategoryService;
import services.ItemService;
import services.LogService;
import utils.EvaluationUtils;

public class TwitterAnalyze {

	public static void main() {

		List<Category> categoryList = CategoryService.getCategoryList();
		List<Item> itemList = new ArrayList<Item>();
		for (Category category : categoryList) {
			if (category != null && category.link != null
					&& !"".equals(category.link)) {
				List<Log> feedList = LogService
						.getLogToday(category.categoryId);
				itemList = ItemService.getItemList(feedList);
			} else {
				itemList = ItemService.getCacheItemListByCategory(category);
			}
			EvaluationUtils.getTweetList(itemList, category);
		}
	}
}
