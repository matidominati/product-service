package com.matidominati.productservie.productservice.service;

import com.matidominati.productservie.productservice.exception.DataNotFoundException;
import com.matidominati.productservie.productservice.mapper.ProductTOMapper;
import com.matidominati.productservie.productservice.model.dto.ProductTO;
import com.matidominati.productservie.productservice.model.entity.ProductAccessoryEntity;
import com.matidominati.productservie.productservice.model.entity.ProductConfigurationEntity;
import com.matidominati.productservie.productservice.model.entity.ProductEntity;
import com.matidominati.productservie.productservice.repository.ProductAccessoryRepository;
import com.matidominati.productservie.productservice.repository.ProductConfigurationRepository;
import com.matidominati.productservie.productservice.repository.ProductRepository;
import com.matidominati.productservie.productservice.service.updater.ProductUpdater;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductConfigurationRepository configurationRepository;
    private final ProductAccessoryRepository accessoryRepository;
    private final ProductTOMapper mapper;

    public List<ProductTO> getAll() {
        log.info("Search process for all products has started");
        List<ProductTO> products = productRepository.findAll().stream()
                .map(mapper::map)
                .toList();
        log.info("{} products found", products.size());
        return products;
    }

    public ProductTO getBaseProductById(Long id) {
        log.info("Search process for product with ID: {} has started", id);
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Product not found"));
        log.info("Product with ID: {} found", id);
        return mapper.map(product);
    }

    public ProductTO customizeProduct(Long baseProductId, List<Long> selectedConfigurationIds, List<Long> selectedAccessoryIds) {
        ProductEntity baseProduct = productRepository.findById(baseProductId)
                .orElseThrow(() -> new DataNotFoundException("Product not found with id: " + baseProductId));

        ProductEntity personalizedProduct = ProductEntity.builder()
                .productName(baseProduct.getProductName())
                .productType(baseProduct.getProductType())
                .productDescription(baseProduct.getProductDescription())
                .basePrice(baseProduct.getBasePrice())
                .build();

        if (selectedConfigurationIds != null && !selectedConfigurationIds.isEmpty()) {
            selectedConfigurationIds.forEach(configurationId -> {
                ProductConfigurationEntity selectedConfiguration = configurationRepository.findById(configurationId)
                        .orElseThrow(() -> new DataNotFoundException("Configuration not found for ID: " + configurationId));
                personalizedProduct.addConfiguration(selectedConfiguration);
            });
        }

        if (selectedAccessoryIds != null && !selectedAccessoryIds.isEmpty()) {
            selectedAccessoryIds.forEach(accessoryId -> {
                ProductAccessoryEntity selectedAccessory = accessoryRepository.findById(accessoryId)
                        .orElseThrow(() -> new DataNotFoundException("Accessory not found for ID: " + accessoryId));

            });
        }

        log.info("Personalized {} with ID: {} has been created.", personalizedProduct.getProductType(), personalizedProduct.getId());
        return mapper.map(personalizedProduct);
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
    public ProductTO create(ProductTO product) {
        ProductEntity newProduct = ProductEntity.create(product);
        productRepository.save(newProduct);
        if (newProduct.getConfigurations() != null && !newProduct.getConfigurations().isEmpty()) {
            newProduct.getConfigurations().forEach((key, configuration) -> {
                ProductConfigurationEntity savedConfiguration = configurationRepository.save(configuration);
                newProduct.addConfiguration(savedConfiguration);
            });
        }
        if (newProduct.getAccessories() != null && !newProduct.getAccessories().isEmpty()) {
            newProduct.getAccessories().forEach((key, accessory) -> {
                ProductAccessoryEntity savedAccessory = accessoryRepository.save(accessory);
                newProduct.addAccessory(key, savedAccessory);
            });
        }
        log.info("New {} with ID: {} has been created.", newProduct.getProductType(), newProduct.getId());
        log.info("Accessories: {}", newProduct.getAccessories());
        return mapper.map(newProduct);
    }

    @Transactional
    public void delete(Long id) {
        Optional<ProductEntity> productToDelete = productRepository.findById(id);
        if (productToDelete.isEmpty()) {
            throw new DataNotFoundException("Product with given ID does not exist.");
        }
        productRepository.delete(productToDelete.get());
        log.info("{} with ID: {} has ben deleted.", productToDelete.get().getProductType(), id);
    }

    @Transactional
    public ProductTO update(Long id, ProductTO updatedProduct) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Product with the provided ID does not exist."));
        log.info("Updating product with ID: {}", id);
        ProductUpdater.updateProduct(product, updatedProduct.getProductName(), updatedProduct.getProductType(),
                updatedProduct.getProductDescription(), updatedProduct.getBasePrice());
        productRepository.save(product);
        log.info("Product data has been updated.");
        return mapper.map(product);
    }
}
