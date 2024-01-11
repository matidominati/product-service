package com.matidominati.productservie.productservice.model;

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
    private String color;
    private int batteryCapacity;
    private Set<Accessory> accessories;
    private String processorType;
    private int memory;
}
