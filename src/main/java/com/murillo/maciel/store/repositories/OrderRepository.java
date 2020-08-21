package com.murillo.maciel.store.repositories;

import com.murillo.maciel.store.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>
{
	
}
