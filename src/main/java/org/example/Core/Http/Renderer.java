package org.example.Core.Http;

import org.example.Resources.Resource;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Renderer
{
	public static String render(Request request, Object controller, String controllerMethod,
	                            Object... args)
	{
		try
		{
			return renderInternal(request, controller, controllerMethod, args);
		}
		catch (Throwable e)
		{
			// TODO: hide errors from non responseerrors

			// TODO: add error formatting for specified contenttype
			e.printStackTrace();
			return "ERROR!!! %s".formatted(e.getMessage());
		}
	}

	// TODO: rewrite: make return struct
	private static String renderInternal(Request request, Object controller, String controllerMethod,
	                                     Object... args) throws NoSuchElementException, InvocationTargetException, IllegalAccessException
	{
		var method = Arrays.stream(controller.getClass().getDeclaredMethods())
				.filter(lMethod -> lMethod.getName().equals(controllerMethod))
				.findFirst().get();
//
		var finalArgs = new ArrayList<Object>();
//
//		for (var param : method.getParameters())
//		{
//			// TODO: rewrite and add support for any position args when DI
//			if (param.getType() == request.getClass())
//			{
//				System.out.println(request.getClass());
//				System.out.println(param.getType());
//				finalArgs.add(param);
//			}
//		}
//
//		// TODO: add validation and cut some non need params, also throw warn
		finalArgs.addAll(Arrays.stream(args).toList());

		var output = method.invoke(controller, request);

		if (output instanceof Resource)
		{
			if (request.contentType == Request.ContentType.JSON && output instanceof Resource.Jsonable jsonable)
			{
				return jsonable.toJson();
			}
			else if (request.contentType == Request.ContentType.HTML && output instanceof Resource.Template template)
			{
				return template.toTemplate();
			}
			else if (output instanceof Resource.Stringable stringable)
			{
				return stringable.toString();
			}
		}

		if (output instanceof String string)
		{
			return string;
		}

		if (output == null)
		{
			System.out.println("Warning! Response output is null");

			return null;
		}

		System.out.println("Warning! Cannot find converted for response!");
		return output.toString();
	}
}
