package com.matidominati.productservie.productservice.controller;

import com.matidominati.productservie.productservice.model.dto.ProductTO;
import com.matidominati.productservie.productservice.model.entity.ProductEntity;
import com.matidominati.productservie.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductTO> getAll(@RequestParam(required = false) String type) {
        if (StringUtils.hasText(type)) {
            return productService.getByType(type);
        }
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductTO getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping("/types")
    public List<String> getAllTypes() {
        return productService.getAvailableTypes();
    }

    @PostMapping
    public ProductTO create(@Valid @RequestBody ProductEntity product) {
        return productService.create(product);
    }

    @PostMapping("/customize/{baseProductId}")
    public ProductTO customize(@PathVariable Long baseProductId,
                               @RequestParam(required = false, defaultValue = "") List<Long> selectedConfigurationIds,
                               @RequestParam(required = false, defaultValue = "") List<Long> selectedAccessoryIds) {
        return productService.customize(baseProductId, selectedConfigurationIds, selectedAccessoryIds);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.delete(productId);
    }

    @PutMapping("/{productId}")
    public ProductTO update(@PathVariable Long productId, @Valid @RequestBody ProductEntity product) {
        return productService.update(productId, product);
    }
}
