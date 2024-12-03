package org.example.Core.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtil
{
	private static ObjectMapper defaultBuilder;

	public static ObjectMapper newBuilder()
	{
		var builder = new ObjectMapper();
		// Some custom settings
		return builder;
	}

	public static ObjectNode get()
	{
		if (defaultBuilder == null)
		{
			defaultBuilder = newBuilder();
		}

		return defaultBuilder.createObjectNode();
	}
}
