package com.pbg.tpvbackend.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.collect.Sets;
import com.pbg.tpvbackend.dao.product.ProductDao;
import com.pbg.tpvbackend.dto.product.ProductDto;
import com.pbg.tpvbackend.dto.product.ProductFilterDto;
import com.pbg.tpvbackend.dto.product.ProductPostDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.product.InvalidProductTypeException;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.mapper.ProductMapper;
import com.pbg.tpvbackend.mapper.ProductSimpleMapper;
import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.product.Product;
import com.pbg.tpvbackend.model.product.ProductFamily;
import com.pbg.tpvbackend.model.product.ProductSimple;
import com.pbg.tpvbackend.model.product.ProductType;
import com.pbg.tpvbackend.model.security.User;
import com.pbg.tpvbackend.service.ProductFamilyService;
import com.pbg.tpvbackend.service.ProductService;
import com.pbg.tpvbackend.service.RestaurantChainService;
import com.pbg.tpvbackend.service.UserService;
import com.pbg.tpvbackend.service.security.UserDataService;
import com.pbg.tpvbackend.utils.AppConstants;

import lombok.AllArgsConstructor;

@Service
@Validated
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

	ProductDao productDao;
	ProductMapper productMapper;
	ProductSimpleMapper productSimpleMapper;

	UserDataService userDataService;
	UserService userService;
	RestaurantChainService restaurantChainService;
	ProductFamilyService productFamilyService;

	@Override
	public ProductDto create(@Valid ProductPostDto productPostDto) throws InvalidProductTypeException, UserNotFoundException, UserWithoutRestaurantChain {
		ProductType productType = null;
		try {
			productType = productPostDto.getProductType();
		} catch(Exception e) {
			throw new InvalidProductTypeException(AppConstants.getERR_INVALID_PRODUCT_TYPE());
		};
		if(productType.equals(ProductType.SIMPLE)) {
			ProductSimple productSimple = productSimpleMapper.asProductSimple(productPostDto);
			productSimple.setChainProduct(restaurantChainService.findChainByUser());
			productSimple.setFamilies(Sets.newHashSet(productFamilyService.findAll(productPostDto.getProductFamilies())));
			productSimple = productDao.save(productSimple);
			return productSimpleMapper.asProductDto(productSimple);
		} else {
			return null;
		}
	}

	@Override
	public Page<ProductDto> searchByNameAndProductFamilies(ProductFilterDto productFilterDto, Integer page,
			Integer max_per_page) throws UserNotFoundException {
		User user = userService.findByUsername();
		RestaurantChain chain = user.getChain();
		List<ProductFamily> productFamilies = productFamilyService.findAll(productFilterDto.getFamilies());
		if(productFamilies.isEmpty()) {
			productFamilies = null;
		}
		Page<Product> products = productDao.findByNameAndProductFamilies(productFilterDto.getName(), productFamilies,
				chain, PageRequest.of(page, max_per_page));
		return products.map((product) -> productMapper.asProductDto(product));
	}

}
