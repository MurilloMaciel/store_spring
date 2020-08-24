package com.murillo.maciel.store.repositories;

import com.murillo.maciel.store.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>
{
	
}
