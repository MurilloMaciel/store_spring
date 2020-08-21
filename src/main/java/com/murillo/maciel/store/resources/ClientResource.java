package com.murillo.maciel.store.resources;

import com.murillo.maciel.store.domain.Category;
import com.murillo.maciel.store.domain.Client;
import com.murillo.maciel.store.services.CategoryService;
import com.murillo.maciel.store.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/clients")
public class ClientResource
{
	@Autowired
	private ClientService service;
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id)
	{
		Client client = service.search(id);
		return ResponseEntity.ok(client);
	}

}
