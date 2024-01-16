package com.matidominati.productservie.productservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductAccessoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accessoryId;
    private String accessoryValue;
    private BigDecimal accessoryPrice;
}
