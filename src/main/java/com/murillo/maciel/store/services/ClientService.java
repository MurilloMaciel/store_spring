package com.murillo.maciel.store.services;

import com.murillo.maciel.store.domain.Category;
import com.murillo.maciel.store.domain.Client;
import com.murillo.maciel.store.repositories.ClientRepository;
import com.murillo.maciel.store.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService
{
	@Autowired
	private ClientRepository repository;
	
	public Client find(Integer id)
	{
		ObjNotFoundException e = new ObjNotFoundException("Object not found, id -> " + id + ", type -> " + Client.class.getName());
		Optional<Client> client = repository.findById(id);
		return client.orElseThrow( () -> e );
	}
}
