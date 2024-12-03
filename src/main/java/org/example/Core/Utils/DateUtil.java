package org.example.Core.Utils;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class DateUtil
{
	public static final String ENGLISH_FORMAT = "dd-MM-yyyy";

	public static Date parse(String date, String format) throws ParseException
	{
		var formatter = new java.text.SimpleDateFormat(format, Locale.ENGLISH);

		return formatter.parse(date);
	}

	public static String format(Date date, String format)
	{
		var formatter = new java.text.SimpleDateFormat(format, Locale.ENGLISH);

		return formatter.format(date);
	}
}
