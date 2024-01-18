package com.matidominati.productservie.productservice.utils;

import com.matidominati.productservie.productservice.model.entity.AccessoryEntity;
import com.matidominati.productservie.productservice.model.entity.ConfigurationEntity;
import com.matidominati.productservie.productservice.model.entity.ProductEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CostUtils {

    public static BigDecimal calculateConfigurationsCost(List<ConfigurationEntity> configurations) {
        BigDecimal configurationsCost = BigDecimal.ZERO;
        for (ConfigurationEntity configuration : configurations) {
            configurationsCost = configurationsCost.add(configuration.getConfigurationPrice());
        }
        return configurationsCost;
    }

    public static BigDecimal calculateAccessoriesCost(List<AccessoryEntity> accessories) {
        BigDecimal accessoriesCost = BigDecimal.ZERO;
        for (AccessoryEntity accessory : accessories) {
            accessoriesCost = accessoriesCost.add(accessory.getAccessoryPrice());
        }
        return accessoriesCost;
    }

    public static void calculateAndSetTotalPrice(ProductEntity baseProduct) {
        BigDecimal configurationsCost = calculateConfigurationsCost(baseProduct.getConfigurations());
        BigDecimal accessoriesCost = calculateAccessoriesCost(baseProduct.getAccessories());
        baseProduct.setTotalPrice(baseProduct.getBasePrice().add(configurationsCost).add(accessoriesCost));
    }
}
