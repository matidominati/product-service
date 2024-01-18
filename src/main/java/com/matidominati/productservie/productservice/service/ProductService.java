package com.matidominati.productservie.productservice.service;

import com.matidominati.productservie.productservice.mapper.ProductMapper;
import com.matidominati.productservie.productservice.model.dto.ProductDTO;
import com.matidominati.productservie.productservice.model.entity.ConfigurationEntity;
import com.matidominati.productservie.productservice.model.entity.ProductEntity;
import com.matidominati.productservie.productservice.repository.AccessoryRepository;
import com.matidominati.productservie.productservice.repository.ConfigurationRepository;
import com.matidominati.productservie.productservice.repository.ProductRepository;
import com.matidominati.productservie.productservice.utils.CostUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.matidominati.productservie.productservice.utils.ProductUtils.*;
import static com.matidominati.productservie.productservice.utils.RepositoryUtils.findByIdOrThrow;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ConfigurationRepository configurationRepository;
    private final AccessoryRepository accessoryRepository;
    private final ProductMapper mapper;

    public List<ProductDTO> getProducts(String productType) {
        if (productType == null) {
            log.info("Search process for all products has started");
            List<ProductDTO> products = productRepository.findAll().stream()
                    .map(mapper::map)
                    .toList();
            log.info("{} products found", products.size());
            return products;
        }
        log.info("Process of searching for a products: {} has started", productType);
        List<ProductDTO> products = productRepository.findByProductType(productType).stream()
                .map(mapper::map)
                .toList();
        log.info("{} products found", products.size());
        return products;
    }

    public ProductDTO getById(Long id) {
        log.info("Search process for product with ID: {} has started", id);
        ProductEntity product = findByIdOrThrow(id, productRepository, ProductEntity.class);
        log.info("Product with ID: {} found", id);
        return mapper.map(product);
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

    public ProductDTO customize(Long baseProductId, List<Long> selectedConfigurationIds, List<Long> selectedAccessoryIds) {
        ProductEntity baseProduct = findByIdOrThrow(baseProductId, productRepository, ProductEntity.class);
        clearAccessoriesAndConfigurations(baseProduct);
        addSelectedConfigurations(baseProduct, selectedConfigurationIds, configurationRepository);
        addSelectedAccessories(baseProduct, selectedAccessoryIds, accessoryRepository);
        CostUtils.calculateAndSetTotalPrice(baseProduct);
        log.info("Personalized {} with ID: {} has been created.", baseProduct.getProductType(), baseProduct.getId());
        return mapper.map(baseProduct);
    }

    @Transactional
    public ProductDTO create(ProductEntity product) {
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
    public ProductDTO update(Long id, ProductEntity updatedProduct) {
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

}




