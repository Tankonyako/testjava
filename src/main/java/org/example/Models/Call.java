package org.example.Models;

import org.example.Core.Models.Model;

import java.util.Collection;

public record Call(
		long id,
		String name,
		double protection,
		Collection<Ticker> tickers
)
		implements Model
{
}
