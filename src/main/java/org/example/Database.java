package org.example;

import org.example.Repository.CallsRepository;
import org.example.Repository.TickersRepository;

public class Database
{
	public static void seed()
	{
		Repositories.Tickers.seed();
		Repositories.Calls.seed();
	}

	public static class Repositories
	{
		public static final TickersRepository Tickers = new TickersRepository();
		public static final CallsRepository Calls = new CallsRepository();
	}
}
