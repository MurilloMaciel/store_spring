package com.murillo.maciel.store.services;

import java.util.List;
import java.util.Optional;

import com.murillo.maciel.store.dto.CategoryDTO;
import com.murillo.maciel.store.services.exceptions.DataIntegrityException;
import com.murillo.maciel.store.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.murillo.maciel.store.domain.Category;
import com.murillo.maciel.store.repositories.CategoryRepository;

@Service
public class CategoryService
{
	@Autowired
	private CategoryRepository repository;
	
	public Category find(Integer id)
	{
		ObjNotFoundException e = new ObjNotFoundException("Object not found, id -> " + id + ", type -> " + Category.class.getName());
		Optional<Category> category = repository.findById(id);
		return category.orElseThrow( () -> e );
	}

	public List<Category> findAll()
	{
		return repository.findAll();
	}

	public Category insert(Category value)
	{
		value.setId(null);
		return repository.save(value); // método save serve tanto para atualizar quanto para inserir, depende do id == null
	}

	public Category update(Category value)
	{
		find(value.getId());
		return repository.save(value); // método save serve tanto para atualizar quanto para inserir, depende do id == null
	}

	public void delete(Integer id)
	{
		find(id);
		try
		{
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e)
		{
			throw new DataIntegrityException("Can't delete category with products");
		}

	}

	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction)
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Category fromDto(CategoryDTO value)
	{
		return new Category(value);
	}
}
