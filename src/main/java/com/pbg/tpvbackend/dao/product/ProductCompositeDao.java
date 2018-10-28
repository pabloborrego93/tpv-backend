package com.pbg.tpvbackend.dao.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pbg.tpvbackend.model.product.Product;
import com.pbg.tpvbackend.model.product.ProductComposite;

@Repository
public interface ProductCompositeDao extends CrudRepository<ProductComposite, Integer> {

	Optional<Product> findByName(String name);
	List<ProductComposite> findByNameStartsWithIgnoreCase(String name);
	
}
