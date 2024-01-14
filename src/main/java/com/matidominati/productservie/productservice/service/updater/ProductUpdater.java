package com.matidominati.productservie.productservice.service.updater;

import com.matidominati.productservie.productservice.model.entity.ProductEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductUpdater {
    public static void updateProduct(ProductEntity product, String productName, String productType, String productDescription, BigDecimal basePrice) {
        product.setProductName(productName);
        product.setProductType(productType);
        product.setProductDescription(productDescription);
        product.setBasePrice(basePrice);
        product.setConfigurations(product.getConfigurations());
        product.setAccessories(product.getAccessories());
    }
}
