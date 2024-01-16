package com.matidominati.productservie.productservice.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductAccessoryTO {
    private Long accessoryId;
    private String accessoryValue;
    private BigDecimal accessoryPrice;
}
