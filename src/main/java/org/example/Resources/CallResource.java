package org.example.Resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import org.example.Core.Http.HtmlRenderer.HTMLTable;
import org.example.Core.Utils.DateUtil;
import org.example.Core.Utils.JsonUtil;
import org.example.Core.Utils.ToMap;
import org.example.Models.Call;
import org.example.Models.Ticker;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record CallResource(Call... resource) implements Resource, Resource.Jsonable, Resource.Template
{
	@Override
	public String toJson()
	{
		var node = JsonUtil.newBuilder().createArrayNode();

		for (var call : resource)
		{
			node.add(JsonUtil.newBuilder().convertValue(new Json(call), JsonNode.class));
		}

		return node.toPrettyString();
	}

	@Override
	public String toTemplate()
	{
		var list = new ArrayList<Map<String, Object>>();
		for (var call : resource)
		{
			list.add(new Json(call).toMap());
		}

		return new HTMLTable(list).render();
	}

	// Using for converting only specified attrs
	public static class Json implements ToMap<String, Object>
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

		public String getStrikeDate()
		{
			return DateUtil.format(call.strikeDate(), DateUtil.ENGLISH_FORMAT);
		}

		@JsonIgnore
		public Map<String, Object> toMap()
		{
			var map = new LinkedHashMap<String, Object>();
			map.put("name", getName());
			map.put("protection", getProtection());
			map.put("tickers", getTickers());
			map.put("strikeDate", getStrikeDate());

			return map;
		}
	}
}
