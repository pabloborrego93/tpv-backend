package com.pbg.tpvbackend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.google.common.collect.Sets;
import com.pbg.tpvbackend.dao.product.ProductCompositeDao;
import com.pbg.tpvbackend.dao.product.ProductDao;
import com.pbg.tpvbackend.dao.product.ProductSimpleDao;
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
import com.pbg.tpvbackend.mapper.ProductCompositeMapper;
import com.pbg.tpvbackend.mapper.ProductMapper;
import com.pbg.tpvbackend.mapper.ProductSimpleMapper;
import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.product.Product;
import com.pbg.tpvbackend.model.product.ProductComposite;
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
	ProductSimpleDao productSimpleDao;
	ProductSimpleMapper productSimpleMapper;
	ProductCompositeDao productCompositeDao;
	ProductCompositeMapper productCompositeMapper;

	UserDataService userDataService;
	UserService userService;
	RestaurantChainService restaurantChainService;
	ProductFamilyService productFamilyService;

	private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

	@Override
	public ProductDto create(@Valid ProductPostDto productPostDto) throws InvalidProductTypeException,
			UserNotFoundException, UserWithoutRestaurantChain, ProductAlreadyExistsException {
		ProductType productType = null;
		try {
			productType = productPostDto.getProductType();
		} catch (Exception e) {
			throw new InvalidProductTypeException(AppConstants.getERR_INVALID_PRODUCT_TYPE());
		}
		;
		try {
			Product product = this.findByName(productPostDto.getName());
			if (product != null) {
				throw new ProductAlreadyExistsException(
						String.format(AppConstants.getPRODUCT_ALREADY_EXISTS(), product.getName()));
			}
		} catch (ProductNotFoundException e) {
			logger.info(String.format(AppConstants.getPRODUCT_NOT_FOUND(), productPostDto.getName()));
		}
		if (productType.equals(ProductType.SIMPLE)) {
			ProductSimple productSimple = productSimpleMapper.asProductSimple(productPostDto);
			productSimple.setChainProduct(restaurantChainService.findChainByUser());
			productSimple
					.setFamilies(Sets.newHashSet(productFamilyService.findAll(productPostDto.getProductFamilies())));
			productSimple = productDao.save(productSimple);
			return productSimpleMapper.asProductDto(productSimple);
		} else {
			ProductComposite productComposite = productCompositeMapper.asProductComposite(productPostDto);
			productComposite.setChainProduct(restaurantChainService.findChainByUser());
			productComposite
					.setFamilies(Sets.newHashSet(productFamilyService.findAll(productPostDto.getProductFamilies())));
			List<Integer> ids = productPostDto.getProducts().stream().map(p -> p.getId()).collect(Collectors.toList());
			productComposite.setProducts(Sets.newHashSet(this.findAll(ids)));
			productComposite = productDao.save(productComposite);
			return productCompositeMapper.asProductDto(productComposite);
		}
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ProductDto update(@Valid ProductUpdateDto productUpdateDto) throws UserNotFoundException, UserWithoutRestaurantChain, ProductNotFoundException, ProductUpdateException {
		Optional<Product> optProduct = this.findOne(productUpdateDto.getId());
		if (!optProduct.isPresent()) {
			throw new ProductNotFoundException(
					String.format(AppConstants.getERR_PRODUCT_NOT_FOUND(), productUpdateDto.getName()));
		} else {
			User user = userService.findByUsername();
			RestaurantChain chain = user.getChainOwned();
			Product product = optProduct.get();
			if (productUpdateDto.getProductType().equals(ProductType.SIMPLE)) {
				// Se quiere actualizar a simple
				if(product.getProductType().equals(ProductType.SIMPLE)) {
					// Y el producto en BD es simple
					ProductSimple productSimple = productSimpleDao.findOne(productUpdateDto.getId(), chain).get();
					productSimple = productSimpleMapper.asProductSimple(productUpdateDto);
					productSimple.setChainProduct(restaurantChainService.findChainByUser());
					productSimple.setFamilies(Sets.newHashSet(productFamilyService.findAll(productUpdateDto.getProductFamilies())));
					productSimple = productSimpleDao.save(productSimple);
					return productMapper.asProductDto(productSimple);
				} else {
					// Y el producto en BD es compuesto
					ProductComposite productComposite = productCompositeDao.findOne(productUpdateDto.getId(), chain).get();
					productComposite = productCompositeMapper.asProductComposite(productUpdateDto);
					productComposite.setChainProduct(restaurantChainService.findChainByUser());
					productComposite.setFamilies(Sets.newHashSet(productFamilyService.findAll(productUpdateDto.getProductFamilies())));
					productComposite.setProducts(Sets.newHashSet());
					productComposite = productCompositeDao.save(productComposite);
					productDao.updateToSimple(productComposite.getId());
					ProductSimple productSimple = productSimpleDao.findOne(productUpdateDto.getId(), chain).get();
					return productSimpleMapper.asProductDto(productSimple);
				}
			} else {
				// Se quiere actualizar a compuesto
				if (product.getProductType().equals(ProductType.SIMPLE)) {
					// Y el producto en BD es simple
					ProductSimple productSimple = productSimpleDao.findOne(productUpdateDto.getId(), chain).get();
					productSimple = productSimpleMapper.asProductSimple(productUpdateDto);
					productSimple.setChainProduct(restaurantChainService.findChainByUser());
					productSimple.setFamilies(Sets.newHashSet(productFamilyService.findAll(productUpdateDto.getProductFamilies())));
					productSimple = productSimpleDao.save(productSimple);
					productDao.updateToComposite(productSimple.getId());
					Optional<ProductComposite> productCompositeOpt = productCompositeDao.findById(productSimple.getId());
					if(productCompositeOpt.isPresent()) {
						ProductComposite productComposite = productCompositeOpt.get();
						List<Integer> ids = productUpdateDto.getProducts().stream().map(p -> p.getId())
								.collect(Collectors.toList());
						productComposite.setProducts(Sets.newHashSet(this.findAll(ids)));
						return productCompositeMapper.asProductDto(productComposite);
					} else {
						throw new ProductUpdateException(String.format(AppConstants.getERR_PRODUCT_UPDATE(), productSimple.getName()));
					}
				} else {
					// Y el producto en BD es compuesto
					ProductComposite productComposite = productCompositeDao.findOne(productUpdateDto.getId(), chain)
							.get();
					productComposite = productCompositeMapper.asProductComposite(productUpdateDto);
					productComposite.setChainProduct(restaurantChainService.findChainByUser());
					productComposite.setFamilies(
							Sets.newHashSet(productFamilyService.findAll(productUpdateDto.getProductFamilies())));
					List<Integer> ids = productUpdateDto.getProducts().stream().map(p -> p.getId())
							.collect(Collectors.toList());
					productComposite.setProducts(Sets.newHashSet(this.findAll(ids)));
					productComposite = productDao.save(productComposite);
					return productCompositeMapper.asProductDto(productComposite);
				}
			}
		}
	}

	@Override
	public Page<ProductDto> searchByNameAndProductFamilies(ProductFilterDto productFilterDto, Integer page,
			Integer max_per_page) throws UserNotFoundException {
		User user = userService.findByUsername();
		RestaurantChain chain = user.getChainOwned();
		List<ProductFamily> productFamilies = productFamilyService.findAll(productFilterDto.getFamilies());
		if (productFamilies.isEmpty()) {
			productFamilies = null;
		}
		Page<Product> products = productDao.findByNameAndProductFamilies(productFilterDto.getName(), productFamilies,
				chain, PageRequest.of(page, max_per_page));
		return products.map((product) -> {
			if (product.getProductType().equals(ProductType.COMPOSITE)) {
				return productCompositeMapper.asProductDto((ProductComposite) product);
			} else {
				return productMapper.asProductDto(product);
			}
		});
	}

	@Override
	public Product findByName(String name) throws ProductNotFoundException {
		Optional<Product> product = productDao.findByName(name.toLowerCase());
		if (product.isPresent())
			return product.get();
		else
			throw new ProductNotFoundException(String.format(AppConstants.getERR_PRODUCT_NOT_FOUND(), name));
	}

	@Override
	public List<ProductNameDto> findNames() throws UserNotFoundException {
		User user = userService.findByUsername();
		RestaurantChain chain = user.getChainOwned();
		return productDao.findNames(chain);
	}

	@Override
	public Optional<Product> findOne(Integer id) throws UserNotFoundException {
		User user = userService.findByUsername();
		RestaurantChain chain = user.getChainOwned();
		Optional<Product> optProduct = productDao.findOne(id, chain);
		if (optProduct.isPresent()) {
			return optProduct;
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<Product> findAll(List<Integer> ids) throws UserNotFoundException {
		List<Product> productList = new ArrayList<Product>();
		for (Integer id : ids) {
			Optional<Product> optProduct = this.findOne(id);
			if (optProduct.isPresent()) {
				productList.add(optProduct.get());
			}
		}
		return productList;
	}

	@Override
	public Optional<ProductSimple> findOneSimple(Integer id) throws UserNotFoundException {
		User user = userService.findByUsername();
		RestaurantChain chain = user.getChainOwned();
		Optional<ProductSimple> optProduct = productSimpleDao.findOne(id, chain);
		if (optProduct.isPresent()) {
			return optProduct;
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<ProductComposite> findOneComposite(Integer id) throws UserNotFoundException {
		User user = userService.findByUsername();
		RestaurantChain chain = user.getChainOwned();
		Optional<ProductComposite> optProduct = productCompositeDao.findOne(id, chain);
		if (optProduct.isPresent()) {
			return optProduct;
		} else {
			return Optional.empty();
		}
	}

}
