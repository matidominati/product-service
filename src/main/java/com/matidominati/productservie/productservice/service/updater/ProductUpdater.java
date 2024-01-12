package com.matidominati.productservie.productservice.service.updater;

import com.matidominati.productservie.productservice.model.configuration.Accessory;
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
                                      String productDescription, BigDecimal price, String processorType, int memory) {
        updateProduct(computer, productName, productType, productDescription, price);
        updateComputerSpecificFields(computer, processorType, memory);
    }

    public static void updateSmartphone(SmartphoneEntity smartphone, String productName, String productType,
                                        String productDescription, BigDecimal price, String color, int batteryCapacity, Set<Accessory> accessories) {
        updateProduct(smartphone, productName, productType, productDescription, price);
        updateSmartphoneSpecificFields(smartphone, color, batteryCapacity, accessories);
    }

    private static void updateComputerSpecificFields(ComputerEntity computer, String processorType, int memory) {
        computer.setMemory(memory);
        computer.setProductType(processorType);
    }

    private static void updateSmartphoneSpecificFields(SmartphoneEntity smartphone, String color, int batteryCapacity, Set<Accessory> accessories) {
        smartphone.setColor(color);
        smartphone.setBatteryCapacity(batteryCapacity);
        smartphone.setAccessories(accessories);
    }
}
