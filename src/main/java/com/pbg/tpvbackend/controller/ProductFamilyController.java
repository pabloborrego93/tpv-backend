package com.pbg.tpvbackend.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvbackend.dto.productFamily.ProductFamilyDto;
import com.pbg.tpvbackend.dto.productFamily.ProductFamilyPostDto;
import com.pbg.tpvbackend.dto.productFamily.ProductFamilyUpdateDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.productFamily.ProductFamilyAlreadyExists;
import com.pbg.tpvbackend.exception.productFamily.ProductFamilyNotExists;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.service.ProductFamilyService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/productFamily")
@AllArgsConstructor
public class ProductFamilyController {

	private ProductFamilyService pfService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ProductFamilyDto create(@RequestBody ProductFamilyPostDto productFamilyPostDto) throws UserNotFoundException, UserWithoutRestaurantChain, ProductFamilyAlreadyExists {
		return pfService.create(productFamilyPostDto);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ProductFamilyDto update(@RequestBody ProductFamilyUpdateDto productFamilyUpdateDto) throws UserNotFoundException, UserWithoutRestaurantChain, ProductFamilyAlreadyExists, ProductFamilyNotExists {
		return pfService.update(productFamilyUpdateDto);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@RequestParam("name") String name) throws UserNotFoundException, UserWithoutRestaurantChain, ProductFamilyNotExists {
		pfService.delete(name);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Page<ProductFamilyDto> list(
		@RequestParam(required=false, defaultValue = "") String name,
		@RequestParam(required=false, defaultValue = "0") Integer page,
		@RequestParam(required=false, defaultValue = "10") Integer max_per_page) throws UserNotFoundException, UserWithoutRestaurantChain 
	{
		return pfService.listPagedByName(name, page, max_per_page);
	}
	
}
