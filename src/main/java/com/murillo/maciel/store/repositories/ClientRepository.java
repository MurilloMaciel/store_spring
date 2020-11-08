package com.murillo.maciel.store.repositories;

import com.murillo.maciel.store.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>
{

    // findBy{nome do atributo dentro da entidade faz o jpa implementar automaticamente pra nós}
    @Transactional()
	Client findByEmail(String email);
}
