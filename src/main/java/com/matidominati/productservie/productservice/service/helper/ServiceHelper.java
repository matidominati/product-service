package com.matidominati.productservie.productservice.service.helper;

import com.matidominati.productservie.productservice.exception.DataNotFoundException;
import com.matidominati.productservie.productservice.model.entity.AccessoryEntity;
import com.matidominati.productservie.productservice.model.entity.ProductEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceHelper {
    public static void updateProduct(ProductEntity product, String productName, String productType, String productDescription, BigDecimal basePrice) {
        product.setProductName(productName);
        product.setProductType(productType);
        product.setProductDescription(productDescription);
        product.setBasePrice(basePrice);
        product.setConfigurations(product.getConfigurations());
        product.setAccessories(product.getAccessories());
    }

    public static void updateAccessory(AccessoryEntity accessory, String accessoryName, String accessoryType, BigDecimal accessoryPrice) {
        accessory.setAccessoryName(accessoryName);
        accessory.setAccessoryType(accessoryType);
        accessory.setAccessoryPrice(accessoryPrice);
    }

    public static void clearAccessoriesAndConfigurations(ProductEntity product) {
        product.getAccessories().clear();
        product.getConfigurations().clear();
    }

    public static <T> T findByIdOrThrow(Long id, JpaRepository<T, Long> jpaRepository, Class<T> entityName) {
        Optional<T> entity = jpaRepository.findById(id);
        if (entity.isEmpty()) {
            throw new DataNotFoundException(entityName.getSimpleName() + " with the provided ID does not exist.");
        }
        return entity.get();
    }
}
