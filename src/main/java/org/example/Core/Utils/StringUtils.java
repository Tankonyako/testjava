package org.example.Core.Utils;

public class StringUtils
{
	public static String capitalize(String str)
	{
		if (str == null || str.isEmpty())
		{
			return str;
		}

		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String camelToSpaces(String str)
	{
		return str.replaceAll("([a-z])([A-Z])", "$1 $2").toLowerCase();
	}

	public static String safeToString(Object obj)
	{
		if (obj == null)
		{
			return null;
		}

		return obj.toString();
	}
}
