package org.example.Core.Http.HtmlRenderer;

import org.example.Core.Utils.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HTMLTable extends HTML
{
	private List<Map<String, Object>> rawTable;

	public HTMLTable(Map<String, Object> map)
	{
		this.rawTable = List.of(map);
	}

	public HTMLTable(List<Map<String, Object>> rawTable)
	{
		this.rawTable = rawTable;
	}

	@Override
	public String render()
	{
		// TODO: rewrite to modern system with trees
		var keys = new LinkedHashMap<String, Integer>();

		// --- Process keys, magicall find all possible keys
		for (var row : rawTable)
		{
			for (var entry : row.entrySet())
			{
				var key = entry.getKey();
				var keyCount = 0;

				if (entry.getValue() instanceof List<?> list)
				{ // TODO: rework to trees and recursive
					for (int i = 1;
					     i <= list.size();
					     i++)
					{
						keyCount++;
					}
				}
				else
				{
					keyCount = 1;
				}

				if (keys.getOrDefault(key, 0) < keyCount)
				{
					keys.put(key, keyCount);
				}
			}
		}

		var values = new ArrayList<List<String>>();
		// fil out values (include empty values)
		for (var row : rawTable)
		{
			var value = new ArrayList<String>();
			;
			for (var entry : row.entrySet())
			{
				var key = entry.getKey();
				if (entry.getValue() instanceof List<?> list)
				{ // TODO: rework to trees and recursive
					for (int i = 0;
					     i < keys.get(key);
					     i++)
					{
						if (i < list.size())
						{
							value.add(StringUtils.safeToString(list.get(i).toString()));
						}
						else
						{
							value.add("");
						}
					}
				}
				else
				{
					value.add(StringUtils.safeToString(entry.getValue()));
				}
			}

			values.add(value);
		}

		// --- Generate HTML table
		var output = new StringBuilder();

		output.append("<table border='1'>");
		output.append("<thead><tr>");

		// do columns
		for (var entry : keys.entrySet())
		{
			var key = StringUtils.capitalize(StringUtils.camelToSpaces(entry.getKey()));

			var max = entry.getValue();
			for (int i = 0;
			     i < max;
			     i++)
			{
				output.append("<th>").append(key);

				if (max > 1)
				{
					output.append(" ").append(i + 1);
				}

				output.append("</th>");
			}
		}
		output.append("</tr></thead>");

		output.append("<tbody>");

		// do rows
		for (var row : values)
		{
			output.append("<tr>");
			for (var column : row)
			{
				output.append("<td>").append(column).append("</td>");
			}
			output.append("</tr>");
		}

		output.append("</tbody>");
		output.append("</table>");

		return output.toString();
	}
}
