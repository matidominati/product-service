package com.matidominati.productservie.productservice.model.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ProductConfiguration {
    private List<Accessory> accessories;
}
