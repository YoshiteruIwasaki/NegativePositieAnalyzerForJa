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
public class Tweet extends Model {

	private static final long serialVersionUID = 3890695880010099962L;

	@Id
	public Long tweetId;

	@Required
	@NotNull
	public Long categoryId;

	public Long itemId;

	@Required
	@NotNull
	public Long tweeterId;

	@Required
	@NotNull
	public String searchWord;

	public Date createdAt;

	@Required
	@NotNull
	public String source;

	@Required
	@NotNull
	@Lob
	public String text;

	public Double point;

	public Double rawPoint;

	public Long tweeterUserId;

	public String tweeterUsetName;

	public String tweeterUserScreenName;

	public String tweeterUserUrl;

	public String tweeterUserImageUrl;

	@CreatedTimestamp
	public Date createDate;

	@Version
	public Date updateDate;
}
