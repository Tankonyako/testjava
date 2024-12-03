package org.example.Core.Repository;

import org.example.Core.Models.Model;

import java.util.*;

public class BaseRepository<T extends Model> implements Repository, Repository.CRUD<T>
{
	// Temp map
	protected List<T> data = List.of();

	@Override
	public boolean delete(long id)
	{
		var newData = data.stream()
				.filter(call -> call.id() != id)
				.toList();

		if (newData.size() != data.size())
		{
			data = newData;
			return true;
		}

		return false;
	}

	@Override
	public Optional<T> find(long id)
	{
		return data.stream()
				.filter(call -> call.id() == id)
				.findFirst();
	}

	@Override
	public Collection<T> where(String name, Object... values)
	{
		return data.stream()
				.filter(model -> Arrays.stream(values).anyMatch(value -> Objects.equals(model.toMap().getOrDefault(name, null), value)))
				.toList();
	}

	@Override
	public Collection<T> list()
	{
		return data;
	}

	@Override
	public void insert(T value)
	{
		var output = new ArrayList<T>(data);
		output.add(value);

		data = output.stream().toList();
	}
}
