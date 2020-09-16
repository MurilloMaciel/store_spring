package com.murillo.maciel.store.services;

import com.murillo.maciel.store.domain.Address;
import com.murillo.maciel.store.domain.City;
import com.murillo.maciel.store.domain.Client;
import com.murillo.maciel.store.domain.enums.ClientType;
import com.murillo.maciel.store.dto.ClientDTO;
import com.murillo.maciel.store.dto.ClientNewDTO;
import com.murillo.maciel.store.repositories.ClientRepository;
import com.murillo.maciel.store.services.exceptions.DataIntegrityException;
import com.murillo.maciel.store.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService
{
    @Autowired
    private ClientRepository repository;

//    @Autowired
//    private AddressRepository addressRepository;

    public Client find(Integer id)
    {
        ObjNotFoundException e = new ObjNotFoundException("Object not found, id -> " + id + ", type -> " + Client.class.getName());
        Optional<Client> client = repository.findById(id);
        return client.orElseThrow(() -> e);
    }

    @Transactional // garante que ele salva tanto cliente quanto seus endereços na mesma transação com banco de dados
    public Client insert(Client value)
    {
        value.setId(null);
//        value = repository.save(value);
//        addressRepository.save(value.getAdresses());
//        return value;

        return repository.save(value); // método save serve tanto para atualizar quanto para inserir, depende do id == null
    }

    public List<Client> findAll()
    {
        return repository.findAll();
    }

    public Client update(Client value)
    {
        Client newClient = find(value.getId());
        updateData(newClient, value);
        return repository.save(newClient); // método save serve tanto para atualizar quanto para inserir, depende do id == null
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
            throw new DataIntegrityException("Can't delete client because has related entities");
        }

    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction)
    {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Client fromDto(ClientDTO value)
    {
        return new Client(value.getId(), value.getName(), value.getEmail(), null, null);
    }

    public Client fromDto(ClientNewDTO value)
    {
        City city = new City(value.getCityId(), null, null);
        Client client = new Client(null, value.getName(), value.getEmail(), value.getCpfOrCnpj(), ClientType.toEnum(value.getClientTypeInt()));
        Address address = new Address(null, value.getStreet(), value.getNumber(), value.getComplement(), value.getNeighborhood(), value.getZipCode(), client, city);
        client.getAdresses().add(address);
        client.getPhones().add(value.getPhone1());
        if (value.getPhone2() != null)
        {
            client.getPhones().add(value.getPhone2());
        }
        if (value.getPhone3() != null)
        {
            client.getPhones().add(value.getPhone3());
        }
        return client;
    }

    private void updateData(Client newClient, Client client)
    {
        newClient.setName(client.getName());
        newClient.setEmail(client.getEmail());
    }
}
