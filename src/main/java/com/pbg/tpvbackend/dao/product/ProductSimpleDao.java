package com.pbg.tpvbackend.dao.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pbg.tpvbackend.model.product.Product;
import com.pbg.tpvbackend.model.product.ProductSimple;

@Repository
public interface ProductSimpleDao extends CrudRepository<ProductSimple, Integer> {

	Optional<Product> findByName(String name);
	List<ProductSimple> findByNameStartsWithIgnoreCase(String name);
	
}
