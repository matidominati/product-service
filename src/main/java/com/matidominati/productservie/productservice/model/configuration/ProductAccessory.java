package com.matidominati.productservie.productservice.model.configuration;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductAccessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accessoryId;
    private String accessoryValue;
    private BigDecimal accessoryPrice;
}
