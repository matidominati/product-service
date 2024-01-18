package com.matidominati.productservie.productservice.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDTO {
    private Long id;
    private String productName;
    private String productType;
    private String productDescription;
    private BigDecimal basePrice;
    private BigDecimal totalPrice;
    private List<AccessoryDTO> accessories;
    private List<ConfigurationDTO> configurations;
}
