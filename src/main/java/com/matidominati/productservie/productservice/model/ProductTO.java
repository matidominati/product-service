package com.matidominati.productservie.productservice.model;

import com.matidominati.productservie.productservice.model.configuration.ComputerConfiguration;
import com.matidominati.productservie.productservice.model.configuration.SmartphoneConfiguration;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class ProductTO {
    private Long id;
    private String productName;
    private String productType;
    private String productDescription;
    private BigDecimal price;
    private Set<ComputerConfiguration> computerConfiguration;
    private Set<SmartphoneConfiguration> smartphoneConfiguration;
}
