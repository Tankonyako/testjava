package org.example.Resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import org.example.Core.Http.HtmlRenderer.HTMLTable;
import org.example.Core.Utils.DateUtil;
import org.example.Core.Utils.JsonUtil;
import org.example.Core.Utils.ToMap;
import org.example.Models.Call;

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

		public List<Map<String, String>> getTickers()
		{
			return call.tickers().stream().map(ticker -> Map.of(
					"name", ticker.name(),
					"strikeDate", DateUtil.format(ticker.strikeDate(), DateUtil.ENGLISH_FORMAT)
			)).toList();
		}

		@JsonIgnore
		public Map<String, Object> toMap()
		{
			var map = new LinkedHashMap<String, Object>();
			map.put("name", getName());
			map.put("protection", getProtection());
			map.put("tickers", getTickers());

			return map;
		}
	}
}
