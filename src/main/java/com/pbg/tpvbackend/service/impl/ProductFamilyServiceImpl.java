package com.pbg.tpvbackend.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.pbg.tpvbackend.dao.product.ProductFamilyDao;
import com.pbg.tpvbackend.dto.productFamily.ProductFamilyDto;
import com.pbg.tpvbackend.dto.productFamily.ProductFamilyPostDto;
import com.pbg.tpvbackend.dto.restaurantChain.RestaurantChainDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.productFamily.ProductFamilyAlreadyExists;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.mapper.ProductFamilyMapper;
import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.product.ProductFamily;
import com.pbg.tpvbackend.service.ProductFamilyService;
import com.pbg.tpvbackend.service.RestaurantChainService;
import com.pbg.tpvbackend.service.UserService;
import com.pbg.tpvbackend.service.security.UserDataService;

import lombok.AllArgsConstructor;

@Service
@Validated
@AllArgsConstructor
public class ProductFamilyServiceImpl implements ProductFamilyService {

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
				newProductFamily.setChainProductFamily(restaurantChainService.findChainByUser());
				newProductFamily = pfDao.save(newProductFamily);
				return productFamilyMapper.asProductFamilyDto(newProductFamily);
			}
		}
	}

	@Override
	public Page<ProductFamilyDto> listPagedByName(String name, Integer page, Integer max) throws UserNotFoundException, UserWithoutRestaurantChain {
		RestaurantChain chain = restaurantChainService.findChainByUser();
		Page<ProductFamily> resultsPage = pfDao.findByNameStartsWithIgnoreCaseAndChainProductFamily(name, chain, PageRequest.of(page, max));
		return resultsPage.map(pF -> productFamilyMapper.asProductFamilyDto(pF));
	}
	
}
