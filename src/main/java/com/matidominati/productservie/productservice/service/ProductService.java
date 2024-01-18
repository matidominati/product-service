package com.matidominati.productservie.productservice.service;

import com.matidominati.productservie.productservice.exception.ConfigurationAlreadyExistsException;
import com.matidominati.productservie.productservice.exception.DataNotFoundException;
import com.matidominati.productservie.productservice.mapper.ProductTOMapper;
import com.matidominati.productservie.productservice.model.dto.ProductTO;
import com.matidominati.productservie.productservice.model.entity.AccessoryEntity;
import com.matidominati.productservie.productservice.model.entity.ConfigurationEntity;
import com.matidominati.productservie.productservice.model.entity.ProductEntity;
import com.matidominati.productservie.productservice.repository.AccessoryRepository;
import com.matidominati.productservie.productservice.repository.ConfigurationRepository;
import com.matidominati.productservie.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.matidominati.productservie.productservice.service.helper.ServiceHelper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ConfigurationRepository configurationRepository;
    private final AccessoryRepository accessoryRepository;
    private final ProductTOMapper mapper;

    public List<ProductTO> getAll() {
        log.info("Search process for all products has started");
        List<ProductTO> products = productRepository.findAll().stream()
                .map(mapper::map)
                .toList();
        log.info("{} products found", products.size());
        return products;
    }

    public ProductTO getById(Long id) {
        log.info("Search process for product with ID: {} has started", id);
        ProductEntity product = findByIdOrThrow(id, productRepository, ProductEntity.class);
        log.info("Product with ID: {} found", id);
        return mapper.map(product);
    }

    public ProductTO customize(Long baseProductId, List<Long> selectedConfigurationIds, List<Long> selectedAccessoryIds) {
        ProductEntity baseProduct = findByIdOrThrow(baseProductId, productRepository, ProductEntity.class);
        clearAccessoriesAndConfigurations(baseProduct);
        addSelectedConfigurations(baseProduct, selectedConfigurationIds);
        addSelectedAccessories(baseProduct, selectedAccessoryIds);
        calculateAndSetTotalPrice(baseProduct);
        log.info("Personalized {} with ID: {} has been created.", baseProduct.getProductType(), baseProduct.getId());
        return mapper.map(baseProduct);
    }

    public List<ProductTO> getByType(String productType) {
        log.info("Process of searching for a products: {} has started", productType);
        List<ProductTO> products = productRepository.findByProductType(productType).stream()
                .map(mapper::map)
                .toList();
        log.info("{} products found", products.size());
        return products;
    }

    public List<String> getAvailableTypes() {
        log.info("Fetching available product types");
        List<String> availableTypes = productRepository.findAll()
                .stream()
                .map(productEntity -> productEntity.getProductType())
                .distinct()
                .collect(Collectors.toList());
        log.info("{} product types found", availableTypes.size());
        return availableTypes;
    }

    @Transactional
    public ProductTO create(ProductEntity product) {
        ProductEntity newProduct = ProductEntity.create(product);
        if (newProduct.getConfigurations() != null && !newProduct.getConfigurations().isEmpty()) {
            newProduct.getConfigurations().forEach(configuration -> configuration.setProduct(newProduct));
            newProduct.getConfigurations().forEach(configurationRepository::save);
        }
        productRepository.save(newProduct);
        return mapper.map(newProduct);
    }

    @Transactional
    public void delete(Long id) {
        ProductEntity productToDelete = findByIdOrThrow(id, productRepository, ProductEntity.class);
        productRepository.delete(productToDelete);
        log.info("{} with ID: {} has been deleted.", productToDelete.getProductType(), id);
    }

    @Transactional
    public ProductTO update(Long id, ProductEntity updatedProduct) {
        ProductEntity product = findByIdOrThrow(id, productRepository, ProductEntity.class);
        log.info("Updating product with ID: {}", id);
        updateProduct(product, updatedProduct.getProductName(), updatedProduct.getProductType(),
                updatedProduct.getProductDescription(), updatedProduct.getBasePrice());
        if (updatedProduct.getConfigurations() != null && !updatedProduct.getConfigurations().isEmpty()) {
            Set<Long> existingConfigurationIds = product.getConfigurations().stream()
                    .map(ConfigurationEntity::getConfigurationId)
                    .collect(Collectors.toSet());
            for (ConfigurationEntity updatedConfiguration : updatedProduct.getConfigurations()) {
                if (!existingConfigurationIds.contains(updatedConfiguration.getConfigurationId())) {
                    updatedConfiguration.setProduct(product);
                    configurationRepository.save(updatedConfiguration);
                }
            }
        }
        productRepository.save(product);
        log.info("Product data has been updated.");
        return mapper.map(product);
    }

    private BigDecimal calculateConfigurationsCost(List<ConfigurationEntity> configurations) {
        BigDecimal configurationsCost = BigDecimal.ZERO;
        for (ConfigurationEntity configuration : configurations) {
            configurationsCost = configurationsCost.add(configuration.getConfigurationPrice());
        }
        return configurationsCost;
    }

    private BigDecimal calculateAccessoriesCost(List<AccessoryEntity> accessories) {
        BigDecimal accessoriesCost = BigDecimal.ZERO;
        for (AccessoryEntity accessory : accessories) {
            accessoriesCost = accessoriesCost.add(accessory.getAccessoryPrice());
        }
        return accessoriesCost;
    }

    private void calculateAndSetTotalPrice(ProductEntity baseProduct) {
        BigDecimal configurationsCost = calculateConfigurationsCost(baseProduct.getConfigurations());
        BigDecimal accessoriesCost = calculateAccessoriesCost(baseProduct.getAccessories());
        baseProduct.setTotalPrice(baseProduct.getBasePrice().add(configurationsCost).add(accessoriesCost));
    }

    private void addSelectedConfiguration(ProductEntity baseProduct, Long configurationId) {
        boolean configurationIdExists = baseProduct.getConfigurations().stream()
                .anyMatch(configuration -> configuration.getConfigurationId().equals(configurationId));
        if (!configurationIdExists) {
            ConfigurationEntity selectedConfiguration = getConfigurationById(configurationId);
            boolean configurationTypeExists = baseProduct.getConfigurations().stream()
                    .anyMatch(configurationT -> configurationT.getConfigurationType().equals(selectedConfiguration.getConfigurationType()));
            if (configurationTypeExists) {
                log.warn("Configuration conflict. Two configurations with the same types: {} were selected", selectedConfiguration.getConfigurationType());
                throw new ConfigurationAlreadyExistsException("Configuration conflict. You can't choose two identical configuration types (" + selectedConfiguration.getConfigurationType() + ")");
            }
            baseProduct.getConfigurations().add(selectedConfiguration);
        }
    }

    private ConfigurationEntity getConfigurationById(Long configurationId) {
        return configurationRepository.findById(configurationId)
                .orElseThrow(() -> new DataNotFoundException("Configuration not found for ID: " + configurationId));
    }

    private void addSelectedConfigurations(ProductEntity baseProduct, List<Long> selectedConfigurationIds) {
        if (selectedConfigurationIds != null && !selectedConfigurationIds.isEmpty()) {
            for (Long configurationId : selectedConfigurationIds) {
                addSelectedConfiguration(baseProduct, configurationId);
            }
        }
    }

    private void addSelectedAccessories(ProductEntity baseProduct, List<Long> selectedAccessoryIds) {
        for (Long accessoryId : selectedAccessoryIds) {
            AccessoryEntity selectedAccessory = findByIdOrThrow(accessoryId, accessoryRepository, AccessoryEntity.class);
            baseProduct.getAccessories().add(selectedAccessory);
        }
    }
}




