package com.matidominati.productservie.productservice.mapper;

import com.matidominati.productservie.productservice.model.dto.ProductDTO;
import com.matidominati.productservie.productservice.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {ConfigurationMapper.class, AccessoryMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductDTO map(ProductEntity productEntity);
}
