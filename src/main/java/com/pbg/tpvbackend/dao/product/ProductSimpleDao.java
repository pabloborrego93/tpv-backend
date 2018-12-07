package com.pbg.tpvbackend.dao.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.product.Product;
import com.pbg.tpvbackend.model.product.ProductSimple;

@Repository
public interface ProductSimpleDao extends CrudRepository<ProductSimple, Integer> {

	Optional<Product> findByName(String name);
	List<ProductSimple> findByNameStartsWithIgnoreCase(String name);
	
	@Query("SELECT p "
		 + "FROM ProductSimple p "
		 + "WHERE p.chainProduct = :restaurantChain "
		 + "AND p.id = :id")
	Optional<ProductSimple> findOne(@Param("id") Integer id, @Param("restaurantChain") RestaurantChain restaurantChain);
		
	@Query("SELECT p "
		 + "FROM ProductSimple p "
		 + "WHERE p.chainProduct = :restaurantChain "
		 + "AND p.id IN (:ids)")
	List<ProductSimple> findMultiple(@Param("ids") List<Integer> ids, @Param("restaurantChain") RestaurantChain restaurantChain);	
	
}
