package org.example.Models;

import org.example.Core.Models.Model;

import java.util.Collection;
import java.util.Date;

public record Call(
		long id,
		String name,
		double protection,
		Collection<Ticker> tickers,
		Date strikeDate
)
		implements Model
{
}
