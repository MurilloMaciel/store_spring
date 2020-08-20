package com.murillo.maciel.store.repositories;

import com.murillo.maciel.store.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>
{
	
}
