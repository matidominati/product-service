package com.matidominati.productservie.productservice.model.entity;

import com.matidominati.productservie.productservice.model.configuration.ComputerConfiguration;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComputerEntity extends ProductEntity {
    private Set<ComputerConfiguration> configurations;
}
