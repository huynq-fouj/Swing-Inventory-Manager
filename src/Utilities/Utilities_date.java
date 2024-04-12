package Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities_date {
	
	public static String getDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String getDate() {
		return Utilities_date.getDate("dd/MM/yyyy");
	}
	
	public static String getDateProfiles() {
		return Utilities_date.getDate("ddMMyyHHmmss");
	}
	
}
