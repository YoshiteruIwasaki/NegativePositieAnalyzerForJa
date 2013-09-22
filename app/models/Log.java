package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import play.data.format.Formats;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.validation.NotNull;

@Entity
public class Log extends Model {

	private static final long serialVersionUID = 3890695880010099962L;

	@Id
	public Long logId;

	@Required
	@NotNull
	public Long itemId;

	@Required
	@NotNull
	public Long categoryId;

	@Required
	@NotNull
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	public Date date;

	@Required
	@NotNull
	public int ranking;

	@CreatedTimestamp
	public Date createDate;

	@Version
	public Date updateDate;
}
