package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Version;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.validation.NotNull;

@Entity
public class Item extends Model {

	private static final long serialVersionUID = 3890695880010099962L;

	@Id
	public Long itemId;

	@Required
	@NotNull
	public Long categoryId;

	@Required
	@NotNull
	public String title;

	public String searchWord;

	public Double point;

	@Required
	@NotNull
	@Lob
	public String description;

	@Required
	@NotNull
	@Lob
	public String link;

	public Date pubDate;

	@Lob
	public String picture;

	public String pictureSource;

	public String newsItemTitle;

	@Lob
	public String newsItemSnippet;

	@Lob
	public String newsItemUrl;

	public String newsItemSource;

	@CreatedTimestamp
	public Date createDate;

	@Version
	public Date updateDate;
}
