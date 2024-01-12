package com.matidominati.productservie.productservice.model;

import lombok.Data;

import java.lang.module.Configuration;
import java.math.BigDecimal;

@Data
public class ProductTO {
    private Long id;
    private String productName;
    private String productType;
    private String productDescription;
    private BigDecimal price;
    private Configuration configuration;
}
