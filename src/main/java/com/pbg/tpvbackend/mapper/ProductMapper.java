package com.pbg.tpvbackend.mapper;

import org.mapstruct.Mapper;

import com.pbg.tpvbackend.dto.product.ProductDto;
import com.pbg.tpvbackend.dto.product.ProductNameDto;
import com.pbg.tpvbackend.dto.product.ProductPostDto;
import com.pbg.tpvbackend.dto.product.ProductUpdateDto;
import com.pbg.tpvbackend.model.product.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    public ProductDto asProductDto(Product product);
    public Product asProduct(ProductPostDto productPostDto);
    public Product asProduct(ProductUpdateDto productUpdateDto);
    public ProductNameDto asProductName(Product product);
    
}
