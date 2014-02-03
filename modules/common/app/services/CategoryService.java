package services;

import java.util.List;

import models.Category;
import play.db.ebean.Model.Finder;
import cache.CacheService;

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

	/**
	 * Category取得
	 *
	 * @return
	 */
	public static Category getCateory(Long categoryId) {
		return find.byId(categoryId);
	}

	/**
	 * Category取得
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Category> getCacheCateoryList() {
		String[] keys = { "all" };
		String createKey = CacheService.createKey(CategoryService.class,
				CacheService.KeyType.LIST, "getCategoryList", keys);
		Object result = CacheService.getCache(createKey);
		if (result == null) {
			result = getCategoryList();
			CacheService.setCache(createKey, result);
		}
		return (List<Category>) result;
	}

	/**
	 * Category取得
	 *
	 * @return
	 */
	public static Category getCacheCateory(Long categoryId) {
		String[] keys = { String.valueOf(categoryId) };
		Class<?>[] param = new Class[] { Long.class };
		Object[] arguments = { categoryId };
		return (Category) CacheService.getObject(CategoryService.class,
				CacheService.KeyType.DETAIL, keys, "getCateory", param,
				arguments);
	}
}
