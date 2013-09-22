package tasks;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Category;
import models.Item;
import models.Log;
import play.Logger;
import services.CategoryService;
import services.ItemService;
import services.LogService;
import utils.AmazonUtils;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class FeedReader {

	/**
	 * RSSフィード保存
	 *
	 * @return
	 */
	public static ArrayList<String> main() {

		ArrayList<String> arrayList = new ArrayList<String>();
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormat.format(date);

		try {
			List<Category> categoryList = CategoryService.getFeedCategoryList();
			for (Category category : categoryList) {

				List<Log> list = LogService.getLogByDate(dateString,
						category.categoryId);
				LogService.deleteList(list);
				Date date2 = dateFormat.parse(dateString);
				URL feedUrl = new URL(category.link);
				SyndFeedInput input = new SyndFeedInput();
				SyndFeed feed = input.build(new XmlReader(feedUrl));
				int i = 0;
				for (Object obj : feed.getEntries()) {
					SyndEntry entry = (SyndEntry) obj;
					String title = entry.getTitle();
					title = AmazonUtils.trimTitle(title, i + 1);

					Item item = ItemService.getItemByTitle(title,
							category.categoryId);
					item = ItemService.saveItemByEntry(item, entry, title,
							category);
					if (item != null) {
						LogService.saveLogByItem(item, date2, i + 1, category);
						arrayList.add(title);
					}
					i++;
				}
			}

		} catch (MalformedURLException e) {
			Logger.error("[FeedReader]", e);
		} catch (IllegalArgumentException e) {
			Logger.error("[FeedReader]", e);
		} catch (FeedException e) {
			Logger.error("[FeedReader]", e);
		} catch (IOException e) {
			Logger.error("[FeedReader]", e);
		} catch (ParseException e) {
			Logger.error("[FeedReader]", e);
		}

		return arrayList;
	}

}
