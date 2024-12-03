package org.example.Core.Http;

public class Request
{
	// TODO: logic for reading Accept header and choosing method
	public ContentType contentType = ContentType.HTML;

	public static enum ContentType
	{
		HTML,
		JSON;
	}
}
