package com.matidominati.productservie.productservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfigurationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long configurationId;
    @NotBlank
    private String configurationValue;
    private BigDecimal configurationPrice;
    @NotBlank
    private String configurationType;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigurationEntity configuration = (ConfigurationEntity) o;
        return configurationId != null && Objects.equals(configurationId, configuration.configurationId);
    }
    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
