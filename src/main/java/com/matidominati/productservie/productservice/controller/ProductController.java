package com.matidominati.productservie.productservice.controller;

import com.matidominati.productservie.productservice.model.dto.ProductTO;
import com.matidominati.productservie.productservice.model.entity.ProductEntity;
import com.matidominati.productservie.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductTO> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{productType}")
    public List<ProductTO> getByType(@PathVariable String productType) {
        return productService.getByType(productType);
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
    public ProductTO create(@RequestBody ProductEntity product) {
        return productService.create(product);
    }

    @PostMapping("/customize/{baseProductId}")
    public ProductTO customize(@PathVariable Long baseProductId,
                               @RequestParam(required = false) List<Long> selectedConfigurationIds,
                               @RequestParam(required = false) List<Long> selectedAccessories) {
        return productService.customize(baseProductId, selectedConfigurationIds, selectedAccessories);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.delete(productId);
    }

//    @PutMapping("/{productId}")
//    public ProductTO update(@PathVariable Long productId, @RequestBody ProductTO product) {
//        return productService.update(productId, product);
//    }
}
