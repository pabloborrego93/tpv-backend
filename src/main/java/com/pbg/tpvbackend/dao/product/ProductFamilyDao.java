package com.pbg.tpvbackend.dao.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.product.ProductFamily;

@Repository
public interface ProductFamilyDao extends PagingAndSortingRepository<ProductFamily, Integer> {
	
	Page<ProductFamily> findByNameStartsWithIgnoreCaseAndChainProductFamily(String name, RestaurantChain chain, Pageable pageable);
	
}
