package com.matidominati.productservie.productservice.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccessoryTO {
    private Long accessoryId;
    private String accessoryName;
    private BigDecimal accessoryPrice;
    private String accessoryType;
}
