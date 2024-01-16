package com.matidominati.productservie.productservice.model.dto;

import com.matidominati.productservie.productservice.model.entity.ProductAccessoryEntity;
import com.matidominati.productservie.productservice.model.entity.ProductConfigurationEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class ProductTO {
    private Long id;
    private String productName;
    private String productType;
    private String productDescription;
    private BigDecimal basePrice;
    private BigDecimal totalPrice;
    private Map<String, ProductAccessoryEntity> accessories;
    private Map<String, ProductConfigurationEntity> configurations;
}
