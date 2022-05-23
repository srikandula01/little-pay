package com.littlepay.au;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeCalculate {

	static long findDifference(String start_date, String end_date) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		long t = 0l;

		try {

			Date d1 = sdf.parse(start_date);
			Date d2 = sdf.parse(end_date);

			long difference_In_Time = d2.getTime() - d1.getTime();

			long difference_In_Seconds = TimeUnit.MILLISECONDS.toSeconds(difference_In_Time) % 60;

			long difference_In_Minutes = TimeUnit.MILLISECONDS.toMinutes(difference_In_Time) % 60;

			long difference_In_Hours = TimeUnit.MILLISECONDS.toHours(difference_In_Time) % 24;

			long difference_In_Days = TimeUnit.MILLISECONDS.toDays(difference_In_Time) % 365;

			long difference_In_Years = TimeUnit.MILLISECONDS.toDays(difference_In_Time) / 365l;

			t = difference_In_Years * 365 * 24 * 60 * 60 + difference_In_Days * 24 * 60 * 60
					+ difference_In_Hours * 60 * 60 + difference_In_Minutes * 60 + difference_In_Seconds;
			if(t<0) {
				t = -1*t;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return t;

	}

}
