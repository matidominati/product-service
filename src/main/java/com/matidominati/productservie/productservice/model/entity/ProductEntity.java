package com.matidominati.productservie.productservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
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
    @NotBlank
    private String productName;
    @NotBlank
    private String productType;
    private String productDescription;
    @NotNull
    @DecimalMin(value = "1.00")
    private BigDecimal basePrice;
    @Transient
    private BigDecimal totalPrice;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "product")
    private List<ConfigurationEntity> configurations;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "product")
    private List<AccessoryEntity> accessories;

    public static ProductEntity create(ProductEntity product) {
        return ProductEntity.builder()
                .productName(product.getProductName())
                .productType(product.getProductType())
                .productDescription(product.getProductDescription())
                .basePrice(product.getBasePrice())
                .configurations(product.getConfigurations())
                .accessories(product.getAccessories())
                .build();
    }

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
