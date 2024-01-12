package com.matidominati.productservie.productservice.model.configuration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class ComputerConfiguration extends ProductConfiguration {
    private String processorType;
    private int memory;
}
