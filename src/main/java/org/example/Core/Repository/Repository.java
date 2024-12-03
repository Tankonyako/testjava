package org.example.Core.Repository;

import org.example.Core.Models.Model;

import java.util.Collection;
import java.util.Optional;

public interface Repository
{
	public interface CRUD<T extends Model>
	{
		public boolean delete(long id);

		public Optional<T> find(long id);

		public Collection<T> where(String name, Object value);

		public Collection<T> list();

		public void insert(T value);
	}
}
