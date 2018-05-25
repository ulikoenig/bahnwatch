package de.ulikoenig.bahnwatch.util;

import java.util.Calendar;

public class Util {
	
	public static Calendar getDayCalender(int hours, int minutes) {
		Calendar result = Calendar.getInstance();
		result.set(2000, 01, 01, hours, minutes, 0);
		result.set(Calendar.SECOND, 0);
		result.set(Calendar.MILLISECOND, 0);
		return result;
	}

}
