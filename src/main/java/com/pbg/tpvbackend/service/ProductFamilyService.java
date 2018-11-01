package com.pbg.tpvbackend.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import com.pbg.tpvbackend.dto.productFamily.ProductFamilyDto;
import com.pbg.tpvbackend.dto.productFamily.ProductFamilyPostDto;
import com.pbg.tpvbackend.dto.productFamily.ProductFamilyUpdateDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.productFamily.ProductFamilyAlreadyExists;
import com.pbg.tpvbackend.exception.productFamily.ProductFamilyNotExists;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;

public interface ProductFamilyService {

	public ProductFamilyDto create(@Valid ProductFamilyPostDto productFamilyPostDto) throws UserNotFoundException, UserWithoutRestaurantChain, ProductFamilyAlreadyExists;
	public ProductFamilyDto update(@Valid ProductFamilyUpdateDto productFamilyUpdateDto) throws UserNotFoundException, UserWithoutRestaurantChain, ProductFamilyAlreadyExists, ProductFamilyNotExists;
	public void delete(String name) throws ProductFamilyNotExists, UserWithoutRestaurantChain, UserNotFoundException;
	public Page<ProductFamilyDto> listPagedByName(String name, Integer page, Integer max) throws UserNotFoundException, UserWithoutRestaurantChain;
	
}