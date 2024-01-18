package com.matidominati.productservie.productservice.controller;

import com.matidominati.productservie.productservice.model.dto.ProductDTO;
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
    public List<ProductDTO> getAll(@RequestParam(required = false) String type) {
        if (StringUtils.hasText(type)) {
            return productService.getByType(type);
        }
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping("/types")
    public List<String> getAllTypes() {
        return productService.getAvailableTypes();
    }

    @PostMapping
    public ProductDTO create(@Valid @RequestBody ProductEntity product) {
        return productService.create(product);
    }

    @PostMapping("/customize/{baseProductId}")
    public ProductDTO customize(@PathVariable Long baseProductId,
                                @RequestParam(required = false, defaultValue = "") List<Long> selectedConfigurationIds,
                                @RequestParam(required = false, defaultValue = "") List<Long> selectedAccessoryIds) {
        return productService.customize(baseProductId, selectedConfigurationIds, selectedAccessoryIds);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.delete(productId);
    }

    @PutMapping("/{productId}")
    public ProductDTO update(@PathVariable Long productId, @Valid @RequestBody ProductEntity product) {
        return productService.update(productId, product);
    }
}
