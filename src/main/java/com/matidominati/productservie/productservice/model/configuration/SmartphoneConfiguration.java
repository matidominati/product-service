package com.matidominati.productservie.productservice.model.configuration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SmartphoneConfiguration extends ProductConfiguration {
    private String color;
    private int batteryCapacity;
}
