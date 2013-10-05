package components;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class ISO8601DateFormat {

	  public static DateFormat getDateFormat() {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	    format.setTimeZone(TimeZone.getTimeZone("UTC"));
	    return format;
	  }
}
