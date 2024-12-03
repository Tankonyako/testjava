package org.example.Models;

import org.example.Core.Models.Model;

public record Ticker(
		long id,
		String name,
		String fullName
)
		implements Model
{
}
