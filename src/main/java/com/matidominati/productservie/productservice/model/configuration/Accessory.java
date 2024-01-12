package com.matidominati.productservie.productservice.model.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accessory {
    private String accessoryType;
    private String accessoryName;
    private BigDecimal price;
}
