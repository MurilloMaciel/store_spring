package com.murillo.maciel.store.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.murillo.maciel.store.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
		Category category = service.find(id);
		return ResponseEntity.ok(category);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id)
	{
		service.delete(id);
		return ResponseEntity.noContent().build();
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

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Category value, @PathVariable Integer id)
	{
		value.setId(id);
		value = service.update(value);
		return ResponseEntity
				.noContent()
				.build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll()
	{
		List<Category> categories = service.findAll();
		List<CategoryDTO> categoryDTOS = categories
				.stream()
				.map( category -> new CategoryDTO(category) )
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(categoryDTOS);
	}

	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<?> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction)
	{
		Page<Category> categories = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoryDTO> categoryDTOS = categories.map( category -> new CategoryDTO(category) );
		return ResponseEntity.ok().body(categoryDTOS);
	}

}
