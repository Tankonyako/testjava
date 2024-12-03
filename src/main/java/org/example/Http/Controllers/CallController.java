package org.example.Http.Controllers;

import org.example.Core.Http.Request;
import org.example.Database;
import org.example.Models.Call;
import org.example.Resources.CallResource;

public class CallController
{
	public CallResource All(Request request)
	{
		return new CallResource(Database.Repositories.Calls.list().toArray(new Call[0]));
	}
}
