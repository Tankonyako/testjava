package org.example.Repository;

import org.example.Core.Repository.BaseRepository;
import org.example.Core.Seedable;
import org.example.Database;
import org.example.Models.Call;

import java.util.List;

public class CallsRepository extends BaseRepository<Call> implements Seedable
{
	@Override
	public void seed()
	{
		data = List.of(
				new Call(
						0,
						"Autocall",
						0.7,
						Database.Repositories.Tickers.where("name", "AAPL_UQ", "TSLQ_US").stream().toList()
				),
				new Call(
						0,
						"Autocall",
						0.2,
						Database.Repositories.Tickers.where("name", "AMZN_US").stream().toList()
				)
		);
	}
}
