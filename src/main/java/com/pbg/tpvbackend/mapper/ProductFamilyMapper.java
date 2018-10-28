package com.pbg.tpvbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.pbg.tpvbackend.dto.productFamily.ProductFamilyDto;
import com.pbg.tpvbackend.model.product.ProductFamily;

@Mapper(uses = { ProductFamily.class }, componentModel = "spring")
public interface ProductFamilyMapper {

	@Mappings({ 
        @Mapping(source = "name", target = "name")
    })
    public ProductFamilyDto asProductFamilyDto(ProductFamily productFamily);

}

