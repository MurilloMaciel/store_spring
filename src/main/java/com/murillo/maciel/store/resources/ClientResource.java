package com.murillo.maciel.store.resources;

import com.murillo.maciel.store.domain.Client;
import com.murillo.maciel.store.dto.ClientDTO;
import com.murillo.maciel.store.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource
{
	@Autowired
	private ClientService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id)
	{
		Client client = service.find(id);
		return ResponseEntity.ok(client);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id)
	{
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO value, @PathVariable Integer id)
	{
		Client client = service.fromDto(value);
		client.setId(id);
		client = service.update(client);
		return ResponseEntity
				.noContent()
				.build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll()
	{
		List<Client> clients = service.findAll();
		List<ClientDTO> clientDTOS = clients
				.stream()
				.map( client -> new ClientDTO(client) )
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(clientDTOS);
	}

	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<?> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction)
	{
		Page<Client> clients = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClientDTO> clientDTOS = clients.map( client -> new ClientDTO(client) );
		return ResponseEntity.ok().body(clientDTOS);
	}


}
