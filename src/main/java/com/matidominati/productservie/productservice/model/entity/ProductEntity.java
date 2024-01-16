package com.matidominati.productservie.productservice.model.entity;

import com.matidominati.productservie.productservice.model.dto.ProductTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashMap;
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
    private BigDecimal totalPrice;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    @MapKeyColumn(name = "configuration_key")
    private Map<String, ProductConfigurationEntity> configurations = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    @MapKeyColumn(name = "accessory_key")
    private Map<String, ProductAccessoryEntity> accessories = new HashMap<>();

    public static ProductEntity create(ProductTO product) {
        return ProductEntity.builder()
                .productName(product.getProductName())
                .productType(product.getProductType())
                .productDescription(product.getProductDescription())
                .basePrice(product.getBasePrice())
                .configurations(product.getConfigurations())
                .accessories(product.getAccessories())
                .build();
    }

    public void addConfiguration(ProductConfigurationEntity configuration) {
        configurations.put(configuration.getKeyId(), configuration);
    }
    public void addAccessory(String key, ProductAccessoryEntity accessory) {
        accessories.put(key, accessory);
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
