package com.pbg.tpvbackend.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvbackend.dto.product.ProductDto;
import com.pbg.tpvbackend.dto.product.ProductFilterDto;
import com.pbg.tpvbackend.dto.product.ProductPostDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.product.InvalidProductTypeException;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

	ProductService productService;
	
	@PostMapping("/search")
	public Page<ProductDto> search(
		@RequestBody ProductFilterDto productFilterDto, 
		@RequestParam(value = "page", required = false, defaultValue = "0") Integer page, 
		@RequestParam(value = "max_per_page", required = false, defaultValue = "10") Integer max_per_page) throws UserNotFoundException {
		return productService.searchByNameAndProductFamilies(productFilterDto, page, max_per_page);
	}
	
	@PostMapping
	public ProductDto create(@RequestBody ProductPostDto productPostDto) throws InvalidProductTypeException, UserNotFoundException, UserWithoutRestaurantChain {
		return productService.create(productPostDto);
	}
	
}
