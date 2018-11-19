package com.pbg.tpvbackend.mapper;

import org.mapstruct.Mapper;

import com.pbg.tpvbackend.dto.product.ProductDto;
import com.pbg.tpvbackend.dto.product.ProductPostDto;
import com.pbg.tpvbackend.model.product.ProductSimple;

@Mapper(uses = { ProductSimple.class }, componentModel = "spring")
public interface ProductSimpleMapper {

	public ProductDto asProductDto(ProductSimple productSimple);
	public ProductSimple asProductSimple(ProductPostDto productPostDto);
	
}
