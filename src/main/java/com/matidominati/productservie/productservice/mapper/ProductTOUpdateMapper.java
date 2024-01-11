package com.matidominati.productservie.productservice.mapper;

import com.matidominati.productservie.productservice.model.ProductTO;
import com.matidominati.productservie.productservice.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductTOUpdateMapper {
    ProductEntity update(ProductTO productTO, @MappingTarget ProductEntity product);
}
