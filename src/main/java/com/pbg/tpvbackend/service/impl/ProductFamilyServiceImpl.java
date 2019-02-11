package com.pbg.tpvbackend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.pbg.tpvbackend.dao.product.ProductFamilyDao;
import com.pbg.tpvbackend.dto.productFamily.ProductFamilyDto;
import com.pbg.tpvbackend.dto.productFamily.ProductFamilyPostDto;
import com.pbg.tpvbackend.dto.productFamily.ProductFamilyUpdateDto;
import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.productFamily.ProductFamilyAlreadyExists;
import com.pbg.tpvbackend.exception.productFamily.ProductFamilyNotExists;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.mapper.ProductFamilyMapper;
import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.product.ProductFamily;
import com.pbg.tpvbackend.service.ProductFamilyService;
import com.pbg.tpvbackend.service.RestaurantChainService;
import com.pbg.tpvbackend.service.UserService;
import com.pbg.tpvbackend.service.security.UserDataService;
import com.pbg.tpvbackend.utils.AppConstants;

import lombok.AllArgsConstructor;

@Service
@Validated
@AllArgsConstructor
public class ProductFamilyServiceImpl implements ProductFamilyService {

	private static final Logger logger = LogManager.getLogger(ProductFamilyServiceImpl.class);
	
	ProductFamilyDao pfDao;
	RestaurantChainService restaurantChainService;
	UserDataService userDataService;
	UserService userService;
	ProductFamilyMapper productFamilyMapper;

	@Override
	public ProductFamilyDto create(@Valid ProductFamilyPostDto productFamilyPostDto) throws UserNotFoundException, UserWithoutRestaurantChain, ProductFamilyAlreadyExists {
		Optional<RestaurantChainDto> chain = restaurantChainService.findByUser();
		if(!chain.isPresent()) {
			throw new UserWithoutRestaurantChain();
		} else {
			List<ProductFamilyDto> families = chain.get().getFamilies();
			Optional<ProductFamilyDto> productFamily = families
				.stream()
				.filter(pF -> pF.getName().toLowerCase().equals(productFamilyPostDto.getName().toLowerCase()))
				.findFirst();
			if(productFamily.isPresent()) {
				throw new ProductFamilyAlreadyExists();
			} else {
				ProductFamily newProductFamily = new ProductFamily();
				newProductFamily.setName(productFamilyPostDto.getName());
				newProductFamily.setCatalogable(productFamilyPostDto.getCatalogable());
				newProductFamily.setChainProductFamily(restaurantChainService.findChainByUser());
				newProductFamily = pfDao.save(newProductFamily);
				return productFamilyMapper.asProductFamilyDto(newProductFamily);
			}
		}
	}

	@Override
	public ProductFamilyDto update(@Valid ProductFamilyUpdateDto productFamilyUpdateDto)
			throws UserNotFoundException, UserWithoutRestaurantChain, ProductFamilyAlreadyExists, ProductFamilyNotExists {
		Optional<RestaurantChainDto> chain = restaurantChainService.findByUser();
		if(!chain.isPresent()) {
			throw new UserWithoutRestaurantChain();
		} else {
			Optional<ProductFamily> optionalProductFamily = pfDao.findByIdAndChainProductFamily(
				productFamilyUpdateDto.getId(), 
				restaurantChainService.findChainByUser()
			);
			if(!optionalProductFamily.isPresent()) {
				throw new ProductFamilyNotExists();
			} else {
				ProductFamily productFamily = optionalProductFamily.get();
				productFamily.setName(productFamilyUpdateDto.getName());
				productFamily.setCatalogable(productFamilyUpdateDto.getCatalogable());
				productFamily = pfDao.save(productFamily);
				return productFamilyMapper.asProductFamilyDto(productFamily);
			}
		}
	}
	
	@Override
	public void delete(String name) throws ProductFamilyNotExists, UserWithoutRestaurantChain, UserNotFoundException {
		Optional<RestaurantChainDto> chain = restaurantChainService.findByUser();
		if(!chain.isPresent()) {
			throw new UserWithoutRestaurantChain();
		} else {
			Optional<ProductFamily> optionalProductFamily = pfDao.findByNameAndChainProductFamily(
				name, 
				restaurantChainService.findChainByUser()
			);
			if(!optionalProductFamily.isPresent()) {
				throw new ProductFamilyNotExists();
			} else {
				ProductFamily productFamily = optionalProductFamily.get();
				pfDao.delete(productFamily);
			}
		}
	}
	
	@Override
	public Page<ProductFamilyDto> listPagedByName(String name, Integer page, Integer max) throws UserNotFoundException, UserWithoutRestaurantChain {
		RestaurantChain chain = restaurantChainService.findChainByUser();
		Page<ProductFamily> resultsPage = pfDao.findByNameStartsWithIgnoreCaseAndChainProductFamily(name, chain, PageRequest.of(page, max));
		return resultsPage.map(pF -> productFamilyMapper.asProductFamilyDto(pF));
	}

	@Override
	public ProductFamily findOne(ProductFamilyDto productFamilyDto) throws UserNotFoundException, UserWithoutRestaurantChain, ProductFamilyNotExists {
		Optional<ProductFamily> optionalProductFamily = pfDao.findByNameAndChainProductFamily(
			productFamilyDto.getName(), 
			restaurantChainService.findChainByUser()
		);
		if(!optionalProductFamily.isPresent()) {
			throw new ProductFamilyNotExists();
		} else {
			return optionalProductFamily.get();
		}
	}

	@Override
	public List<ProductFamily> findAll(List<ProductFamilyDto> productFamiliesDto) {
		List<ProductFamily> productFamilies = new ArrayList<ProductFamily>();
		for(ProductFamilyDto productFamilyDto: productFamiliesDto) {
			try {
				ProductFamily productFamily = this.findOne(productFamilyDto);
				productFamilies.add(productFamily);
			} catch(Exception e) {
				String message = String.format(
					AppConstants.getERR_PRODUCT_FAMILY_NOT_FOUND(), 
					userDataService.getUsername(), 
					productFamilyDto.getName());
				logger.error(message);
			}
		}
		return productFamilies;
	}

	@Override
	public ProductFamilyDto toDto(ProductFamily productFamily) {
		return productFamilyMapper.asProductFamilyDto(productFamily);
	}

	@Override
	public List<ProductFamilyDto> listToDto(List<ProductFamily> productFamilies) {
		List<ProductFamilyDto> productFamiliesDto = new ArrayList<ProductFamilyDto>();
		for(ProductFamily productFamily: productFamilies) {
			productFamiliesDto.add(this.toDto(productFamily));
		}
		return productFamiliesDto;
	}
	
}
