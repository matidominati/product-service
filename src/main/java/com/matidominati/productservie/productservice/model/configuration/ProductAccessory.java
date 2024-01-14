package com.matidominati.productservie.productservice.model.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAccessory {
    private String accessoryKey;
    private String accessoryName;
    private BigDecimal accessoryPrice;
}
