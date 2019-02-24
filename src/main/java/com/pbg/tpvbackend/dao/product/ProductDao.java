package com.pbg.tpvbackend.dao.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pbg.tpvbackend.dto.product.ProductNameDto;
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
		 + "LEFT JOIN p.products ps "
		 + "WHERE ((:name IS NULL) OR (LOWER(p.name) LIKE CONCAT('%',:name,'%'))) "
		 + "AND ((:productFamilies IS NULL) OR (fam IN (:productFamilies))) "
		 + "AND p.chainProduct = :restaurantChain")
	Page<Product> findByNameAndProductFamilies(
		@Param("name") String name, 
		@Param("productFamilies") List<ProductFamily> productFamilies,
		@Param("restaurantChain") RestaurantChain restaurantChain,
		Pageable pageable
	);
	
	@Query("SELECT DISTINCT p "
		 + "FROM Product p "
		 + "INNER JOIN FETCH p.families fam "
		 + "LEFT JOIN FETCH p.products prod "
		 + "WHERE p.chainProduct = :restaurantChain "
		 + "AND fam.catalogable = 1 "
		 + "AND p.catalogable = 1")
	List<Product> findByProductFamiliesCatalogables(
		@Param("restaurantChain") RestaurantChain restaurantChain
	);
	
	@Query("SELECT new com.pbg.tpvbackend.dto.product.ProductNameDto(p.id, p.name) FROM Product as p WHERE p.chainProduct = :restaurantChain")
	List<ProductNameDto> findNames(@Param("restaurantChain") RestaurantChain restaurantChain);
	
	@Query("SELECT p "
		 + "FROM Product p "
		 + "WHERE p.chainProduct = :restaurantChain "
		 + "AND p.id = :id")
	Optional<Product> findOne(@Param("id") Integer id, @Param("restaurantChain") RestaurantChain restaurantChain);
	
	@Query("SELECT p "
		 + "FROM Product p "
		 + "WHERE p.chainProduct = :restaurantChain "
		 + "AND p.id IN (:ids)")
	List<Product> findMultiple(@Param("ids") List<Integer> ids, @Param("restaurantChain") RestaurantChain restaurantChain);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Product p SET p.productType = 'COMPOSITE' WHERE p.id = :id")
	void updateToComposite(@Param("id") Integer id);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Product p SET p.productType = 'SIMPLE' WHERE p.id = :id")
	void updateToSimple(@Param("id") Integer id);
	
}
