package com.matidominati.productservie.productservice.model.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComputerEntity extends ProductEntity {
    private String processorType;
    private int memory;

}
