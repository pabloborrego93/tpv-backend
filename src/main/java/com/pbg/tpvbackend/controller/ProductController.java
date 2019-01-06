package com.pbg.tpvbackend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvbackend.dto.product.ProductDto;
import com.pbg.tpvbackend.dto.product.ProductFilterDto;
import com.pbg.tpvbackend.dto.product.ProductNameDto;
import com.pbg.tpvbackend.dto.product.ProductPostDto;
import com.pbg.tpvbackend.dto.product.ProductUpdateDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.product.InvalidProductTypeException;
import com.pbg.tpvbackend.exception.product.ProductAlreadyExistsException;
import com.pbg.tpvbackend.exception.product.ProductNotFoundException;
import com.pbg.tpvbackend.exception.product.ProductUpdateException;
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
	
	@PreAuthorize("hasRole('ROLE_RESTAURANT_CHAIN_ADMIN')")
	@PostMapping
	public ProductDto create(@Valid @RequestBody ProductPostDto productPostDto) throws InvalidProductTypeException, UserNotFoundException, UserWithoutRestaurantChain, ProductNotFoundException, ProductAlreadyExistsException {
		return productService.create(productPostDto);
	}
	
	@PreAuthorize("hasRole('ROLE_RESTAURANT_CHAIN_ADMIN')")
	@PutMapping
	public ProductDto update(@Valid @RequestBody ProductUpdateDto productUpdateDto) throws InvalidProductTypeException, UserNotFoundException, UserWithoutRestaurantChain, ProductNotFoundException, ProductAlreadyExistsException, ProductUpdateException {
		return productService.update(productUpdateDto);
	}
	
	@GetMapping("/names")
	public List<ProductNameDto> findNames() throws UserNotFoundException {
		return productService.findNames();
	}
	
}
