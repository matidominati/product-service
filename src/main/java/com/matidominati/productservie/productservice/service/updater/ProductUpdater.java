package com.matidominati.productservie.productservice.service.updater;

import com.matidominati.productservie.productservice.model.configuration.Accessory;
import com.matidominati.productservie.productservice.model.configuration.ComputerConfiguration;
import com.matidominati.productservie.productservice.model.configuration.SmartphoneConfiguration;
import com.matidominati.productservie.productservice.model.entity.ComputerEntity;
import com.matidominati.productservie.productservice.model.entity.ProductEntity;
import com.matidominati.productservie.productservice.model.entity.SmartphoneEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductUpdater {
    public static void updateProduct(ProductEntity product, String productName, String productType, String productDescription, BigDecimal price) {
        product.setProductName(productName);
        product.setProductType(productType);
        product.setProductDescription(productDescription);
        product.setPrice(price);
    }

    public static void updateComputer(ComputerEntity computer, String productName, String productType,
                                      String productDescription, BigDecimal price, Set<ComputerConfiguration> configurations) {
        updateProduct(computer, productName, productType, productDescription, price);
        updateComputerConfiguration(computer, configurations);
    }

    public static void updateSmartphone(SmartphoneEntity smartphone, String productName, String productType,
                                        String productDescription, BigDecimal price, Set<SmartphoneConfiguration> configurations) {
        updateProduct(smartphone, productName, productType, productDescription, price);
        updateSmartphoneConfiguration(smartphone, configurations);
    }

    private static void updateComputerConfiguration(ComputerEntity computer, Set<ComputerConfiguration> configurations) {
        computer.setConfigurations(configurations);
    }

    private static void updateSmartphoneConfiguration(SmartphoneEntity smartphone, Set<SmartphoneConfiguration> configurations) {
       smartphone.setConfigurations(configurations);
    }
}
