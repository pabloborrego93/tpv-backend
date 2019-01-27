package com.pbg.tpvbackend.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import com.pbg.tpvbackend.dto.product.ProductDto;
import com.pbg.tpvbackend.dto.product.ProductFilterDto;
import com.pbg.tpvbackend.dto.product.ProductNameDto;
import com.pbg.tpvbackend.dto.product.ProductPostDto;
import com.pbg.tpvbackend.dto.product.ProductUpdateDto;
import com.pbg.tpvbackend.dto.product.ProductsForOrderingDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.product.InvalidProductTypeException;
import com.pbg.tpvbackend.exception.product.ProductAlreadyExistsException;
import com.pbg.tpvbackend.exception.product.ProductNotFoundException;
import com.pbg.tpvbackend.exception.product.ProductUpdateException;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.model.product.Product;
import com.pbg.tpvbackend.model.product.ProductComposite;
import com.pbg.tpvbackend.model.product.ProductSimple;

public interface ProductService {

	public ProductDto create(@Valid ProductPostDto productPostDto) throws InvalidProductTypeException, UserNotFoundException, UserWithoutRestaurantChain, ProductNotFoundException, ProductAlreadyExistsException;
	public ProductDto update(@Valid ProductUpdateDto productUpdateDto) throws UserNotFoundException, UserWithoutRestaurantChain, ProductNotFoundException, ProductUpdateException;
	public Page<ProductDto> searchByNameAndProductFamilies(ProductFilterDto productFilterDto, Integer page, Integer max_per_page) throws UserNotFoundException;
	public Product findByName(String name) throws ProductNotFoundException;
	public List<ProductNameDto> findNames() throws UserNotFoundException;
	public List<ProductsForOrderingDto> findByProductFamiliesCatalogables() throws UserNotFoundException;
	
	public Optional<Product> findOne(Integer id) throws UserNotFoundException;
	public Optional<ProductSimple> findOneSimple(Integer id) throws UserNotFoundException;
	public Optional<ProductComposite> findOneComposite(Integer id) throws UserNotFoundException;
	public List<Product> findAll(List<Integer> ids) throws UserNotFoundException;
	
}
