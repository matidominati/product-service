package com.matidominati.productservie.productservice.mapper;

import com.matidominati.productservie.productservice.model.dto.ProductConfigurationTO;
import com.matidominati.productservie.productservice.model.entity.ProductConfigurationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductConfigurationTOMapper {

    ProductConfigurationTO map(ProductConfigurationEntity productConfigurationEntity);
}
