package com.pbg.tpvbackend.dao.product;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.product.ProductFamily;

@Repository
public interface ProductFamilyDao extends PagingAndSortingRepository<ProductFamily, Integer> {
	
	Page<ProductFamily> findByNameStartsWithIgnoreCaseAndChainProductFamily(String name, RestaurantChain chain, Pageable pageable);
	Optional<ProductFamily> findByNameAndChainProductFamily(String name, RestaurantChain chain);
	Optional<ProductFamily> findByIdAndChainProductFamily(Integer id, RestaurantChain chain);
	
	@Query("SELECT count(pf) FROM ProductFamily pf WHERE pf.chainProductFamily = :restaurantChain")
	Integer countByChain(RestaurantChain restaurantChain);
	
}
