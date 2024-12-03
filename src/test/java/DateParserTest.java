import org.example.Core.Utils.DateUtil;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateParserTest
{
	@Test
	public void Test() throws ParseException
	{
		var date = DateUtil.parse("09-07-2024", DateUtil.ENGLISH_FORMAT);
		var calendar = Calendar.getInstance();
		calendar.setTime(date);

		assertEquals(7, calendar.get(Calendar.MONTH) + 1);
		assertEquals(9, calendar.get(Calendar.DATE));
		assertEquals(2024, calendar.get(Calendar.YEAR));
	}
}
