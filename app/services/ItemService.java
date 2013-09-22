package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Category;
import models.Item;
import models.Log;

import org.jdom.Element;

import play.db.ebean.Model.Finder;
import rikyu.model.Sentence;
import utils.RikyuUtils;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;

public class ItemService {
	public static Finder<Long, Item> find = new Finder<Long, Item>(Long.class,
			Item.class);

	public static Item getItemByTitle(String title, Long categoryId) {

		List<Item> list = find.where().eq("title", title)
				.eq("categoryId", categoryId).order().desc("itemId").findList();
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * Item取得
	 *
	 * @return
	 */
	public static List<Item> getItemList(List<Log> logList) {

		ArrayList<Item> arrayList = new ArrayList<Item>();
		if (logList != null && logList.size() > 0) {
			for (Log log : logList) {
				Item item = ItemService.find.byId(log.itemId);
				if (item != null) {
					arrayList.add(item);
				}
			}
		}
		return arrayList;

	}

	/**
	 * RSSデータ登録
	 *
	 * @param node
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Item saveItemByEntry(Item item, SyndEntry entry,
			String title, Category category) {
		boolean isNew = false;
		if (item == null) {
			item = new Item();
			if (title != null) {
				item.title = title;
				item.searchWord = title;
			}
			isNew = true;
		}
		SyndContent description = entry.getDescription();
		if (description != null && description.getValue() != null) {
			item.description = description.getValue();
		}
		String link = entry.getLink();
		if (link != null) {
			item.link = link;
		}
		Date publishedDate = entry.getPublishedDate();
		if (publishedDate != null) {
			item.pubDate = publishedDate;
		}

		ArrayList<Element> foreignMarkups = (ArrayList<Element>) entry
				.getForeignMarkup();

		for (Element foreignMarkup : foreignMarkups) {

			if (foreignMarkup.getNamespacePrefix().equals("ht")) {
				if (foreignMarkup.getName().equals("picture")) {
					item.picture = foreignMarkup.getValue();
				}
				if (foreignMarkup.getName().equals("picture_source")) {
					item.pictureSource = foreignMarkup.getValue();
				}
				if (foreignMarkup.getName().equals("news_item")) {
					List<Element> contentList = (List<Element>) foreignMarkup
							.getChildren();
					for (Element element : contentList) {
						if (element.getName().equals("news_item_title")) {
							item.newsItemTitle = element.getValue();
						}
						if (element.getName().equals("news_item_snippet")) {
							item.newsItemSnippet = element.getValue();
						}
						if (element.getName().equals("news_item_url")) {
							item.newsItemUrl = element.getValue();
						}
						if (element.getName().equals("news_item_source")) {
							item.newsItemSource = element.getValue();
						}

					}
				}
			}
		}

		item.categoryId = category.categoryId;
		Sentence sentence = RikyuUtils.analyze(title);
		item.point = sentence.getPoint();
		if (isNew) {
			item.save();
		} else {
			item.update();
		}
		return item;
	}

	public static List<Item> getItemListByCategory(Category categoryBean) {
		return ItemService.find.where()
				.eq("categoryId", categoryBean.categoryId).findList();
	}
}
