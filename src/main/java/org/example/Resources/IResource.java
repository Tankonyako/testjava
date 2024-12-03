package org.example.Resources;

public interface IResource<T>
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
