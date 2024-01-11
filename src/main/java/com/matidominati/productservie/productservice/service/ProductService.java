package com.matidominati.productservie.productservice.service;

import com.matidominati.productservie.productservice.mapper.ProductMapper;
import com.matidominati.productservie.productservice.model.ProductDTO;
import com.matidominati.productservie.productservice.model.ProductEntity;
import com.matidominati.productservie.productservice.repository.ProductRepository;
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
    private final ProductMapper mapper;

    public List<ProductDTO> getAllProducts() {
        log.info("Search process for all products has started");
        List<ProductDTO> products = productRepository.findAll().stream()
                .map(mapper::productToDTO)
                .toList();
        log.info("{} products found", products.size());
        return products;
    }

    public ProductDTO getProductByType(String productType) {
        log.info("Process of searching for a products: {} has started", productType);
        ProductEntity product = productRepository.findByType(productType)
                .orElseThrow(() -> new RuntimeException("Product of a given type does not exist."));
        return mapper.productToDTO(product);
    }

    @Transactional
    public ProductDTO createProduct(ProductDTO createdProduct) {
        ProductEntity product = null;
        productRepository.save(product);
        return mapper.productToDTO(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Optional<ProductEntity> productToDelete = productRepository.findById(id);
        if (productToDelete.isEmpty()) {
            throw new RuntimeException("Product with given ID does not exist.");
        }
        productRepository.delete(productToDelete.get());
    }

    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO updatedProduct) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with the provided ID does not exist."));
        productRepository.save(product);
        log.info("Product data has been updated.");
        return mapper.productToDTO(product);
    }
}
