package com.matidominati.productservie.productservice.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SmartphoneEntity extends ProductEntity{
    private String color;
    private int batteryCapacity;
    private Set<Accessory> accessories;

}
