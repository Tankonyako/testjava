package org.example.Models;

import org.example.Core.Models.Model;

import java.util.Date;

public record Ticker(
		long id,
		String name,
		String fullName,
		Date strikeDate
)
		implements Model
{
}
