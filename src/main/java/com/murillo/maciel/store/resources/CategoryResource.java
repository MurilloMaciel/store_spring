package com.murillo.maciel.store.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.murillo.maciel.store.domain.Category;
import com.murillo.maciel.store.services.CategoryService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping(value = "/categories")
public class CategoryResource
{
	@Autowired
	private CategoryService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id)
	{
		Category category = service.search(id);
		return ResponseEntity.ok(category);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Category value)
	{
		value = service.insert(value);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(value.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
