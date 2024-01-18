package com.matidominati.productservie.productservice.mapper;

import com.matidominati.productservie.productservice.model.dto.AccessoryDTO;
import com.matidominati.productservie.productservice.model.entity.AccessoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccessoryMapper {

    AccessoryDTO map(AccessoryEntity accessoryEntity);
}
