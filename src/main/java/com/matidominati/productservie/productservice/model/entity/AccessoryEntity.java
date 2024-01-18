package com.matidominati.productservie.productservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AccessoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accessoryId;
    @NotBlank
    private String accessoryName;
    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal accessoryPrice;
    @NotBlank
    private String accessoryType;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    public static AccessoryEntity create(AccessoryEntity accessory) {
        return AccessoryEntity.builder()
                .accessoryName(accessory.getAccessoryName())
                .accessoryType(accessory.getAccessoryName())
                .accessoryPrice(accessory.getAccessoryPrice())
                .accessoryType(accessory.getAccessoryType())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessoryEntity accessory = (AccessoryEntity) o;
        return accessoryId != null && Objects.equals(accessoryId, accessory.accessoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
