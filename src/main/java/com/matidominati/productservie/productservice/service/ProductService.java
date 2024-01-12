package com.matidominati.productservie.productservice.service;

import com.matidominati.productservie.productservice.exception.DataNotFoundException;
import com.matidominati.productservie.productservice.mapper.ProductTOMapper;
import com.matidominati.productservie.productservice.mapper.ProductTOUpdateMapper;
import com.matidominati.productservie.productservice.model.ProductTO;
import com.matidominati.productservie.productservice.model.entity.ComputerEntity;
import com.matidominati.productservie.productservice.model.entity.ProductEntity;
import com.matidominati.productservie.productservice.model.entity.SmartphoneEntity;
import com.matidominati.productservie.productservice.repository.ProductRepository;
import com.matidominati.productservie.productservice.service.updater.ProductUpdater;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductTOMapper mapper;
    private final ProductTOUpdateMapper updateMapper;

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
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Product not found"));
        log.info("Product with ID: {} not found", id);
        return mapper.map(product);
    }

    public List<ProductTO> getByType(String productType) {
        log.info("Process of searching for a products: {} has started", productType);
        List<ProductTO> products = productRepository.findByType(productType).stream()
                .map(mapper::map)
                .toList();
        log.info("{} products found", products.size());
        return products;
    }

    @Transactional
    public ProductTO create(ProductTO productTO) {
        ProductEntity product = mapper.map(productTO);
        productRepository.save(product);
        log.info("New {} with ID: {} has been created.", product.getProductType(), product.getId());
        return mapper.map(product);
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

        if (product instanceof ComputerEntity) {
            log.info("Updating computer with ID: {}", id);
            ProductUpdater.updateComputer((ComputerEntity) product, updatedProduct.getProductName(), updatedProduct.getProductType(),
                    updatedProduct.getProductDescription(), updatedProduct.getPrice(), updatedProduct.getComputerConfiguration());
        }
        if (product instanceof SmartphoneEntity) {
            log.info("Updating smartphone with ID: {}", id);
            ProductUpdater.updateSmartphone((SmartphoneEntity) product, updatedProduct.getProductName(), updatedProduct.getProductType(),
                    updatedProduct.getProductDescription(), updatedProduct.getPrice(), updatedProduct.getSmartphoneConfiguration());
        }
        log.info("Updating product with ID: {}", id);
        ProductUpdater.updateProduct(product, updatedProduct.getProductName(), updatedProduct.getProductType(),
                updatedProduct.getProductDescription(), updatedProduct.getPrice());
        productRepository.save(product);
        log.info("Product data has been updated.");
        return mapper.map(product);
    }
}
