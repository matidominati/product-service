package com.matidominati.productservie.productservice.model.entity;

import com.matidominati.productservie.productservice.model.configuration.ProductConfiguration;
import com.matidominati.productservie.productservice.model.configuration.ProductAccessory;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String productType;
    private String productDescription;
    private BigDecimal basePrice;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    @MapKeyColumn(name = "configuration_key")
    private Map<String, ProductConfiguration> configurations;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    @MapKeyColumn(name = "accessory_key")
    private Map<String, ProductAccessory> accessories;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity product = (ProductEntity) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
