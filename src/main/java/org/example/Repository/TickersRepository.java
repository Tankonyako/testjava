package org.example.Repository;

import org.example.Core.Repository.BaseRepository;
import org.example.Core.Seedable;
import org.example.Models.Ticker;

import java.util.List;

public class TickersRepository extends BaseRepository<Ticker> implements Seedable
{
	@Override
	public void seed()
	{
		data = List.of(
				new Ticker(0, "AAPL_UQ", "Apple inc."),
				new Ticker(1, "TSLQ_US", "TESLA"),
				new Ticker(2, "GOOG_US", "Google"),
				new Ticker(3, "AMZN_US", "Amazon")
		);
	}
}
