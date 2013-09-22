package services;

import java.util.List;

import models.Category;
import play.db.ebean.Model.Finder;

public class CategoryService {

	public static Finder<Long, Category> find = new Finder<Long, Category>(
			Long.class, Category.class);

	/**
	 * Category取得
	 *
	 * @return
	 */
	public static List<Category> getCategoryList() {
		return find.all();
	}

	/**
	 * Category取得
	 *
	 * @return
	 */
	public static List<Category> getFeedCategoryList() {
		return find.where().isNotNull("link").findList();
	}
}
