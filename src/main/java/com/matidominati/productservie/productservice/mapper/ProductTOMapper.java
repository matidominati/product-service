package com.matidominati.productservie.productservice.mapper;

import com.matidominati.productservie.productservice.model.dto.ProductTO;
import com.matidominati.productservie.productservice.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductTOMapper {

    ProductTO map(ProductEntity productEntity);
    ProductEntity map(ProductTO productTO);
}
