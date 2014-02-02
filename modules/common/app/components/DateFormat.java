package components;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormat {

	public static Date getLastDateStart() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getPreviousDateStart() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -2);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getTodayStart() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static String getLastDateStartString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy'年'MM'月'dd'日'");
		return simpleDateFormat.format(getLastDateStart());
	}

	public static String getRankingDateString(Date date) {
		SimpleDateFormat sdf1 = new SimpleDateFormat(
				"yyyy'年'MM'月'dd'日'");
		return sdf1.format(date);
	}

	public static String getTimestampString(Date date) {
		SimpleDateFormat sdf1 = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		return sdf1.format(date);
	}
}
