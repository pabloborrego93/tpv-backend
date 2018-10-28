package com.pbg.tpvbackend.dao.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pbg.tpvbackend.model.product.Product;

@Repository
public interface ProductDao extends CrudRepository<Product, Integer> {

	Optional<Product> findByName(String name);
	List<Product> findByNameStartsWithIgnoreCase(String name);
	
}
