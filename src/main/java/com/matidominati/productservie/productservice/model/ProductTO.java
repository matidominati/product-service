package com.matidominati.productservie.productservice.model;

import com.matidominati.productservie.productservice.model.configuration.ProductAccessory;
import com.matidominati.productservie.productservice.model.configuration.ProductConfiguration;
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
    private Map<String, ProductAccessory> accessories;
    private Map<String, ProductConfiguration> configurations;
}
