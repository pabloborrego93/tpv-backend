package com.pbg.tpvbackend.dao.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.product.Product;
import com.pbg.tpvbackend.model.product.ProductComposite;

@Repository
public interface ProductCompositeDao extends CrudRepository<ProductComposite, Integer> {

	Optional<Product> findByName(String name);
	List<ProductComposite> findByNameStartsWithIgnoreCase(String name);
	
	@Query("SELECT p "
		 + "FROM ProductComposite p "
		 + "WHERE p.chainProduct = :restaurantChain "
		 + "AND p.id = :id")
	Optional<ProductComposite> findOne(@Param("id") Integer id, @Param("restaurantChain") RestaurantChain restaurantChain);
		
	@Query("SELECT p "
		 + "FROM ProductComposite p "
		 + "WHERE p.chainProduct = :restaurantChain "
		 + "AND p.id IN (:ids)")
	List<ProductComposite> findMultiple(@Param("ids") List<Integer> ids, @Param("restaurantChain") RestaurantChain restaurantChain);
	
}
