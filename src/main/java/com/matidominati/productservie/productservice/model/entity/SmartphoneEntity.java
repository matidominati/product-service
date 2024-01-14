package com.matidominati.productservie.productservice.model.entity;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmartphoneEntity extends ProductEntity {
    private Set<SmartphoneConfiguration> configurations;
}
