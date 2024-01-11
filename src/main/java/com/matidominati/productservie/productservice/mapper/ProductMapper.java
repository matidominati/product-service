package com.matidominati.productservie.productservice.mapper;

import com.matidominati.productservie.productservice.model.ProductDTO;
import com.matidominati.productservie.productservice.model.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    ProductDTO productToDTO(ProductEntity product);
}
