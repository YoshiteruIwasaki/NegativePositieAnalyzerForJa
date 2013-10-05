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
public class Category extends Model {

	private static final long serialVersionUID = 3890695880010099962L;

	@Id
	public Long categoryId;

	@Required
	@NotNull
	public String title;

	@Lob
	public String link;

	public String consumerKey;

	public String consumerSecret;

	public String accessToken;

	public String accessTokenSecret;

	@CreatedTimestamp
	public Date createDate;

	@Version
	public Date updateDate;
}
