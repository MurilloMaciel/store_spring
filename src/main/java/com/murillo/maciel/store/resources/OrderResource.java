package com.murillo.maciel.store.resources;

import com.murillo.maciel.store.domain.Category;
import com.murillo.maciel.store.domain.Order;
import com.murillo.maciel.store.dto.CategoryDTO;
import com.murillo.maciel.store.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping(value = "/orders")
public class OrderResource
{
	@Autowired
	private OrderService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id)
	{
		Order order = service.find(id);
		return ResponseEntity.ok(order);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Order value)
	{
		Order order = service.insert(value);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(order.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
