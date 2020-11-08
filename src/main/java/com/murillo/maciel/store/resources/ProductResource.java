package com.murillo.maciel.store.resources;

import com.murillo.maciel.store.domain.Category;
import com.murillo.maciel.store.domain.Order;
import com.murillo.maciel.store.domain.Product;
import com.murillo.maciel.store.dto.CategoryDTO;
import com.murillo.maciel.store.dto.ProductDTO;
import com.murillo.maciel.store.resources.utils.URL;
import com.murillo.maciel.store.services.OrderService;
import com.murillo.maciel.store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/products")
public class ProductResource
{
	@Autowired
	private ProductService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id)
	{
		Product product = service.find(id);
		return ResponseEntity.ok(product);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findPage(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "categories", defaultValue = "") String categories,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction)
	{
		List<Integer> ids = URL.StringToIntList(categories);
		String decodedName = URL.decodeParam(name);
		Page<Product> products = service.search(decodedName, ids, page, linesPerPage, orderBy, direction);
		Page<ProductDTO> productDTOS = products.map(product -> new ProductDTO(product) );
		return ResponseEntity.ok().body(productDTOS);
	}

}
