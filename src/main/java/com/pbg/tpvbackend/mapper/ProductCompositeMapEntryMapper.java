package com.pbg.tpvbackend.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.pbg.tpvbackend.dto.product.ProductNameAmountDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.model.product.Product;
import com.pbg.tpvbackend.model.product.ProductComposite;
import com.pbg.tpvbackend.model.product.ProductType;
import com.pbg.tpvbackend.service.ProductService;

@Component
public class ProductCompositeMapEntryMapper {

	@Autowired
	@Lazy
	private ProductService productService;

	public ProductNameAmountDto toProductNameAmountDto(Map.Entry<Product, Integer> productEntry) {
		ProductComposite productComposite = null;
		if (productEntry.getKey().getProductType().equals(ProductType.COMPOSITE)) {
			productComposite = (ProductComposite) productEntry.getKey();
		}
		return new ProductNameAmountDto(productEntry.getKey().getId(), productEntry.getKey().getName(),
				productEntry.getValue(), productEntry.getKey().getForKitchen(), productEntry.getKey().getCatalogable(),
				productEntry.getKey().getProductType(),
				(productComposite != null && productComposite.getProducts() != null)
						? this.toProductNameAmountDto(productComposite.getProducts())
						: null);
	}

	public List<ProductNameAmountDto> toProductNameAmountDto(Map<Product, Integer> productEntries) {
		List<ProductNameAmountDto> products = new ArrayList<>();
		for (Map.Entry<Product, Integer> productEntry : productEntries.entrySet()) {
			products.add(toProductNameAmountDto(productEntry));
		}
		return products;
	}

	public Map<Product, Integer> productAmountDtoToAmountMap(List<ProductNameAmountDto> productsAmountDto)
			throws UserNotFoundException {
		return productService.productAmountDtoToAmountMap(productsAmountDto);
	}

}
