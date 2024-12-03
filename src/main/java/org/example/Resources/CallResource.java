package org.example.Resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import org.example.Core.Utils.JsonUtil;
import org.example.Models.Call;
import org.example.Models.Ticker;

import java.util.Date;
import java.util.List;

public record CallResource(Call resource) implements Resource, Resource.Jsonable, Resource.Template
{
	@Override
	public String toJson()
	{
		return JsonUtil.newBuilder().convertValue(new Json(resource), JsonNode.class).toPrettyString();
	}

	@Override
	public String toTemplate()
	{
		return null;
	}

	// Using for converting only specified attrs
	public static class Json
	{
		@JsonIgnore
		private Call call;

		@JsonIgnore
		public Json(Call call)
		{
			this.call = call;
		}

		public long getId()
		{
			return call.id();
		}

		public String getName()
		{
			return call.name();
		}

		public double getProtection()
		{
			return call.protection();
		}

		public List<String> getTickers()
		{
			return call.tickers().stream().map(Ticker::name).toList();
		}

		public Date getStrikeDate()
		{
			return call.strikeDate();
		}
	}
}
