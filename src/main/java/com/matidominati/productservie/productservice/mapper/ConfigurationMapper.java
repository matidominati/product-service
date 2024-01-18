package com.matidominati.productservie.productservice.mapper;

import com.matidominati.productservie.productservice.model.dto.ConfigurationDTO;
import com.matidominati.productservie.productservice.model.entity.ConfigurationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConfigurationMapper {

    ConfigurationDTO map(ConfigurationEntity configurationEntity);
}
