package com.matidominati.productservie.productservice.utils;

import com.matidominati.productservie.productservice.exception.ConfigurationAlreadyExistsException;
import com.matidominati.productservie.productservice.exception.DataNotFoundException;
import com.matidominati.productservie.productservice.model.entity.AccessoryEntity;
import com.matidominati.productservie.productservice.model.entity.ConfigurationEntity;
import com.matidominati.productservie.productservice.model.entity.ProductEntity;
import com.matidominati.productservie.productservice.repository.AccessoryRepository;
import com.matidominati.productservie.productservice.repository.ConfigurationRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static com.matidominati.productservie.productservice.utils.RepositoryUtils.findByIdOrThrow;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class ProductUtils {
    public static void updateProduct(ProductEntity product, String productName, String productType, String productDescription, BigDecimal basePrice) {
        product.setProductName(productName);
        product.setProductType(productType);
        product.setProductDescription(productDescription);
        product.setBasePrice(basePrice);
        product.setConfigurations(product.getConfigurations());
        product.setAccessories(product.getAccessories());
    }

    public static void clearAccessoriesAndConfigurations(ProductEntity product) {
        product.getAccessories().clear();
        product.getConfigurations().clear();
    }

    public static void addSelectedConfigurations(ProductEntity baseProduct, List<Long> selectedConfigurationIds, ConfigurationRepository configurationRepository) {
        if (selectedConfigurationIds != null && !selectedConfigurationIds.isEmpty()) {
            for (Long configurationId : selectedConfigurationIds) {
                addSelectedConfiguration(baseProduct, configurationId, configurationRepository);
            }
        }
    }

    public static void addSelectedAccessories(ProductEntity baseProduct, List<Long> selectedAccessoryIds, AccessoryRepository accessoryRepository) {
        for (Long accessoryId : selectedAccessoryIds) {
            AccessoryEntity selectedAccessory = findByIdOrThrow(accessoryId, accessoryRepository, AccessoryEntity.class);
            baseProduct.getAccessories().add(selectedAccessory);
        }
    }

    public static ConfigurationEntity getConfigurationById(Long configurationId, ConfigurationRepository configurationRepository) {
        return configurationRepository.findById(configurationId)
                .orElseThrow(() -> new DataNotFoundException("Configuration not found for ID: " + configurationId));
    }

    public static void addSelectedConfiguration(ProductEntity baseProduct, Long configurationId, ConfigurationRepository configurationRepository) {
        boolean configurationIdExists = baseProduct.getConfigurations().stream()
                .anyMatch(configuration -> configuration.getConfigurationId().equals(configurationId));
        if (!configurationIdExists) {
            ConfigurationEntity selectedConfiguration = getConfigurationById(configurationId, configurationRepository);
            boolean configurationTypeExists = baseProduct.getConfigurations().stream()
                    .anyMatch(configurationT -> configurationT.getConfigurationType().equals(selectedConfiguration.getConfigurationType()));
            if (configurationTypeExists) {
                log.warn("Configuration conflict. Two configurations with the same types: {} were selected", selectedConfiguration.getConfigurationType());
                throw new ConfigurationAlreadyExistsException("Configuration conflict. You can't choose two identical configuration types (" + selectedConfiguration.getConfigurationType() + ")");
            }
            baseProduct.getConfigurations().add(selectedConfiguration);
        }
    }
}
