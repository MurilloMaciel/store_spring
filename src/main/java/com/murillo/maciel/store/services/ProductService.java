package com.murillo.maciel.store.services;

import com.murillo.maciel.store.domain.Category;
import com.murillo.maciel.store.domain.Product;
import com.murillo.maciel.store.repositories.CategoryRepository;
import com.murillo.maciel.store.repositories.ProductRepository;
import com.murillo.maciel.store.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService
{
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public Product find(Integer id)
	{
		ObjNotFoundException e = new ObjNotFoundException("Object not found, id -> " + id + ", type -> " + Product.class.getName());
		Optional<Product> product = productRepository.findById(id);
		return product.orElseThrow(() -> e);
	}

	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction)
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAllById(ids);
		return productRepository.search(name, categories, pageRequest);
	}
}