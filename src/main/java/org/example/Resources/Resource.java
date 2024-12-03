package org.example.Resources;

public interface Resource
{
	public interface Jsonable
	{
		public String toJson();
	}

	public interface Stringable
	{
		public String toString();
	}

	public interface Template
	{
		public String toTemplate();
	}
}
