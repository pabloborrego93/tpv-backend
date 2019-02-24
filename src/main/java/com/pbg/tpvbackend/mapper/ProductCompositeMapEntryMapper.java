package com.pbg.tpvbackend.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pbg.tpvbackend.dto.product.ProductNameAmountDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.model.product.Product;
import com.pbg.tpvbackend.service.ProductService;

@Component
public class ProductCompositeMapEntryMapper {

	@Autowired
	@Lazy private ProductService productService;
	
	public ProductNameAmountDto toProductNameAmountDto(Map.Entry<Product, Integer> productEntry) {
		return new ProductNameAmountDto(productEntry.getKey().getId(), productEntry.getKey().getName(), productEntry.getValue());
	}
	
	public List<ProductNameAmountDto> toProductNameAmountDto(Map<Product, Integer> productEntries) {
		List<ProductNameAmountDto> products = new ArrayList<>();
		for(Map.Entry<Product, Integer> productEntry: productEntries.entrySet()) {
			products.add(toProductNameAmountDto(productEntry));
		}
		return products;
	}
	
	public Map<Product, Integer> productAmountDtoToAmountMap(List<ProductNameAmountDto> productsAmountDto) throws UserNotFoundException {
		return productService.productAmountDtoToAmountMap(productsAmountDto);
	}
	
}
