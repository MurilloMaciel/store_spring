package com.murillo.maciel.store;

import java.util.Arrays;

import com.murillo.maciel.store.domain.City;
import com.murillo.maciel.store.domain.Product;
import com.murillo.maciel.store.domain.State;
import com.murillo.maciel.store.repositories.CityRepository;
import com.murillo.maciel.store.repositories.ProductRepository;
import com.murillo.maciel.store.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.murillo.maciel.store.domain.Category;
import com.murillo.maciel.store.repositories.CategoryRepository;

@SpringBootApplication
public class StoreSpringApplication implements CommandLineRunner
{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    public static void main(String[] args)
    {
        SpringApplication.run(StoreSpringApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception
    {
        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");

        Product p1 = new Product(null, "Computador", 2000.00);
        Product p2 = new Product(null, "Impressora", 800.00);
        Product p3 = new Product(null, "Mouse", 80.00);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().add(p2);

        p1.getCategories().add(cat1);
        p2.getCategories().addAll(Arrays.asList(cat1, cat2));
        p3.getCategories().add(cat1);

        State state1 = new State(null, "Minas Gerais");
        State state2 = new State(null, "São Paulo");

        City city1 = new City(null, "Uberlândia", state1);
        City city2 = new City(null, "São Paulo", state2);
        City city3 = new City(null, "Campinas", state2);

        state1.getCities().add(city1);
        state1.getCities().addAll(Arrays.asList(city2, city3));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));
        productRepository.saveAll(Arrays.asList(p1, p2, p3));
        stateRepository.saveAll(Arrays.asList(state1, state2));
        cityRepository.saveAll(Arrays.asList(city1, city2, city3));
    }

}
