package com.murillo.maciel.store.services;

import com.murillo.maciel.store.domain.Order;
import com.murillo.maciel.store.repositories.OrderRepository;
import com.murillo.maciel.store.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService
{
	@Autowired
	private OrderRepository repository;
	
	public Order find(Integer id)
	{
		ObjNotFoundException e = new ObjNotFoundException("Object not found, id -> " + id + ", type -> " + Order.class.getName());
		Optional<Order> order = repository.findById(id);
		return order.orElseThrow( () -> e );
	}
}
