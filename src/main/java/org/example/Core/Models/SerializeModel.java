package org.example.Core.Models;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public interface SerializeModel
{
	public int hashCode();

	public String toString();

	// TODO: save to local cache
	default public Map<String, Object> toMap()
	{
		return toMap(false);
	}

	default public Map<String, Object> toMap(boolean forceAll)
	{
		var declaredInternal = SerializeModel.class.getDeclaredMethods();
		var output = new HashMap<String, Object>();
		for (var method : getClass().getMethods())
		{
			if (method.getReturnType() == Void.class)
			{
				continue;
			}

			if (method.getParameterCount() > 0)
			{
				continue;
			}

			// Ignore java and this method
			if (method.getDeclaringClass() == Object.class ||
					Arrays.stream(declaredInternal).anyMatch(dMethod -> method.getName().equals(dMethod.getName())))
			{
				continue;
			}

			if (!forceAll && method.isAnnotationPresent(IgnoreSerialize.class))
			{
				continue;
			}

			// ONly value methods from record, also ignore ignorable
			try
			{
				synchronized (this)
				{
					output.put(method.getName(), method.invoke(this));
				}
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
				System.out.printf("Warning, cannot read DTO %s%n", method.getName());
			}
			catch (InvocationTargetException e)
			{
				e.printStackTrace();
				System.out.printf("Warning, cannot read DTO %s%n", method.getName());
			}
		}

		return output;
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface IgnoreSerialize
	{

	}
}
