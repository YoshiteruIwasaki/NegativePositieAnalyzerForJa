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
public class Ranking extends Model {

	private static final long serialVersionUID = 3890695880010099962L;

	@Id
	public Long rankingId;

	@Required
	@NotNull
	public Long itemId;

	@Required
	@NotNull
	public Long categoryId;

	@Required
	@NotNull
	public int totalCount;

	@Required
	@NotNull
	public int countNeutral;

	@Required
	@NotNull
	public int countNegative;

	@Required
	@NotNull
	public int countPositive;

	@Required
	@NotNull
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	public Date date;

	@Required
	@NotNull
	public int ranking;

	/**
	 * 前日比
	 * 1:アップ
	 * 0:変化なし
	 * -1:ダウン
	 */
	@Required
	@NotNull
	public int comparison;

	@CreatedTimestamp
	public Date createDate;

	@Version
	public Date updateDate;
}
