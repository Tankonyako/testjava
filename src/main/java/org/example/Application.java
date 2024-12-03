package org.example;

import org.example.Core.Http.Renderer;
import org.example.Core.Http.Request;
import org.example.Http.Controllers.CallController;

public class Application
{
	public String output(Request.ContentType contentType, Object controller, String method)
	{
		var request = new Request();
		request.contentType = contentType;

		return Renderer.render(request, controller, method);
	}

	public void run()
	{
		Database.seed();

		var controller = new CallController();
		System.out.println(output(Request.ContentType.JSON, controller, "All"));
	}
}
