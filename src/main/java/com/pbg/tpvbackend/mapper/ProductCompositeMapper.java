package com.pbg.tpvbackend.mapper;

import org.mapstruct.Mapper;

import com.pbg.tpvbackend.dto.product.ProductDto;
import com.pbg.tpvbackend.dto.product.ProductNameDto;
import com.pbg.tpvbackend.dto.product.ProductPostDto;
import com.pbg.tpvbackend.dto.product.ProductUpdateDto;
import com.pbg.tpvbackend.model.product.ProductComposite;

@Mapper(componentModel = "spring")
public interface ProductCompositeMapper {

	public ProductDto asProductDto(ProductComposite productComposite);
	public ProductComposite asProductComposite(ProductPostDto productPostDto);
	public ProductComposite asProductComposite(ProductUpdateDto productUpdateDto);
	public ProductNameDto asProductName(ProductComposite productComposite);
	
}
