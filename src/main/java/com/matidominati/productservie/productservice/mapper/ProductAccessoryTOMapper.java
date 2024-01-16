package com.matidominati.productservie.productservice.mapper;

import com.matidominati.productservie.productservice.model.dto.ProductAccessoryTO;
import com.matidominati.productservie.productservice.model.entity.ProductAccessoryEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ProductAccessoryTOMapper {

    ProductAccessoryTO map(ProductAccessoryEntity productAccessoryEntity);
}
