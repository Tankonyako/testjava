package org.example.Repository;

import org.example.Core.Repository.BaseRepository;
import org.example.Core.Seedable;
import org.example.Core.Utils.DateUtil;
import org.example.Models.Ticker;

import java.text.ParseException;
import java.util.List;

public class TickersRepository extends BaseRepository<Ticker> implements Seedable
{
	@Override
	public void seed()
	{
		try
		{
			data = List.of(
					new Ticker(0, "AAPL_UQ", "Apple inc.", DateUtil.parse("09-07-2024", DateUtil.ENGLISH_FORMAT)),
					new Ticker(1, "TSLQ_US", "TESLA", DateUtil.parse("05-12-2024", DateUtil.ENGLISH_FORMAT)),
					new Ticker(2, "GOOG_US", "Google", DateUtil.parse("10-08-2024", DateUtil.ENGLISH_FORMAT)),
					new Ticker(3, "AMZN_US", "Amazon", DateUtil.parse("03-07-2024", DateUtil.ENGLISH_FORMAT))
			);
		}
		// throw crash app
		catch (ParseException e)
		{
			throw new RuntimeException(e);
		}
	}
}
