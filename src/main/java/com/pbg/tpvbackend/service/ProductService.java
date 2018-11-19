package com.pbg.tpvbackend.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import com.pbg.tpvbackend.dto.product.ProductDto;
import com.pbg.tpvbackend.dto.product.ProductFilterDto;
import com.pbg.tpvbackend.dto.product.ProductPostDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.product.InvalidProductTypeException;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;

public interface ProductService {

	public ProductDto create(@Valid ProductPostDto productPostDto) throws InvalidProductTypeException, UserNotFoundException, UserWithoutRestaurantChain;
	public Page<ProductDto> searchByNameAndProductFamilies(ProductFilterDto productFilterDto, Integer page, Integer max_per_page) throws UserNotFoundException;
	
}
