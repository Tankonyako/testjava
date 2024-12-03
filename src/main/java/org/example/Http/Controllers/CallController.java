package org.example.Http.Controllers;

import org.example.Core.Http.Request;
import org.example.Database;
import org.example.Resources.CallResource;

public class CallController
{
	public CallResource All(Request request)
	{
		return new CallResource(Database.Repositories.Calls.find(0).orElse(null));
	}
}
