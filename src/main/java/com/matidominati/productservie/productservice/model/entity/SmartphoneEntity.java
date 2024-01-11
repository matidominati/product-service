package com.matidominati.productservie.productservice.model.entity;

import com.matidominati.productservie.productservice.model.Accessory;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmartphoneEntity extends ProductEntity {
    private String color;
    private int batteryCapacity;
    private Set<Accessory> accessories;
}
