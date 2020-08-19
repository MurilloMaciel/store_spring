package com.murillo.maciel.store;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.murillo.maciel.store.domain.Category;
import com.murillo.maciel.store.repositories.CategoryRepository;

@SpringBootApplication
public class StoreSpringApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(StoreSpringApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception
	{
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
	}

}
