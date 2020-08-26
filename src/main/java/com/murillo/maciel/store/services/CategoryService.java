package com.murillo.maciel.store.services;

import java.util.Optional;

import com.murillo.maciel.store.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.murillo.maciel.store.domain.Category;
import com.murillo.maciel.store.repositories.CategoryRepository;

@Service
public class CategoryService
{
	@Autowired
	private CategoryRepository repository;
	
	public Category search(Integer id)
	{
		ObjNotFoundException e = new ObjNotFoundException("Object not found, id -> " + id + ", type -> " + Category.class.getName());
		Optional<Category> category = repository.findById(id);
		return category.orElseThrow( () -> e );
	}

	public Category insert(Category value)
	{
		value.setId(null);
		return repository.save(value);
	}
}
