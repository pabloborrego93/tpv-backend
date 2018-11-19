package com.pbg.tpvbackend.dao.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.product.Product;
import com.pbg.tpvbackend.model.product.ProductFamily;

@Repository
public interface ProductDao extends PagingAndSortingRepository<Product, Integer> {

	Optional<Product> findByName(String name);
	List<Product> findByNameStartsWithIgnoreCase(String name);
	
	@Query("SELECT DISTINCT p "
		 + "FROM Product p "
		 + "LEFT JOIN p.families fam "
		 + "WHERE ((:name IS NULL) OR (LOWER(p.name) LIKE CONCAT('%',:name,'%'))) "
		 + "AND ((:productFamilies IS NULL) OR (fam IN (:productFamilies))) "
		 + "AND p.chainProduct = :restaurantChain")
	Page<Product> findByNameAndProductFamilies(
		@Param("name") String name, 
		@Param("productFamilies") List<ProductFamily> productFamilies,
		@Param("restaurantChain") RestaurantChain restaurantChain,
		Pageable pageable
	);
	
}
