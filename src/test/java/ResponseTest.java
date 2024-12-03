import org.example.Core.Http.Renderer;
import org.example.Core.Http.Request;
import org.example.Resources.Resource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseTest
{
	private static String emptyJson = "{}";
	private static String emptyHtml = "<div></div>";

	@Test
	public void Test()
	{
		var request = new Request();
		var controller = new CustomController();

		request.contentType = Request.ContentType.JSON;
		assertEquals(emptyJson, Renderer.render(request, controller, "Test"));
		request.contentType = Request.ContentType.HTML;
		assertEquals(emptyHtml, Renderer.render(request, controller, "Test"));

		// Force replace content type inside (check if request passes in)
		assertEquals(emptyJson, Renderer.render(request, controller, "PassTest"));
	}

	public static class CustomController
	{
		public TestResource Test(Request request)
		{
			return new TestResource();
		}

		public TestResource PassTest(Request request)
		{
			request.contentType = Request.ContentType.JSON;
			return new TestResource();
		}
	}

	public record TestResource() implements Resource, Resource.Jsonable, Resource.Template
	{
		@Override
		public String toJson()
		{
			return emptyJson;
		}

		@Override
		public String toTemplate()
		{
			return emptyHtml;
		}

	}
}
