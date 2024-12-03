package org.example.Repository;

import org.example.Core.Repository.BaseRepository;
import org.example.Core.Seedable;
import org.example.Core.Utils.DateUtil;
import org.example.Database;
import org.example.Models.Call;

import java.text.ParseException;
import java.util.List;

public class CallsRepository extends BaseRepository<Call> implements Seedable
{
	@Override
	public void seed()
	{
		try
		{
			data = List.of(
					new Call(
							0,
							"Autocall",
							0.7,
							Database.Repositories.Tickers.where("name", "AAPL_UQ", "TSLQ_US").stream().toList(),
							DateUtil.parse("09-07-2024", DateUtil.ENGLISH_FORMAT)
					),
					new Call(
							0,
							"Autocall",
							0.2,
							Database.Repositories.Tickers.where("name", "AMZN_US").stream().toList(),
							DateUtil.parse("12-08-2024", DateUtil.ENGLISH_FORMAT)
					)
			);
		}
		// Crash application
		catch (ParseException e)
		{
			throw new RuntimeException(e);
		}
	}
}
