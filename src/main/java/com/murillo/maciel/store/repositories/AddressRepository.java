package com.murillo.maciel.store.repositories;

import com.murillo.maciel.store.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>
{
	
}
