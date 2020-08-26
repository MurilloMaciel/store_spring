package com.murillo.maciel.store.resources;

import com.murillo.maciel.store.domain.Order;
import com.murillo.maciel.store.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/orders")
public class OrderResource
{
	@Autowired
	private OrderService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id)
	{
		Order order = service.search(id);
		return ResponseEntity.ok(order);
	}

}