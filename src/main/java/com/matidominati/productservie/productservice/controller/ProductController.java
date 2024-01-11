package com.matidominati.productservie.productservice.controller;

import com.matidominati.productservie.productservice.model.ProductTO;
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

    @GetMapping("/type/{productType}")
    public List<ProductTO> getProductsByType(@PathVariable String productType) {
        return productService.getByType(productType);
    }

    @PostMapping("/create")
    public void create(@RequestBody ProductTO product) {
        productService.create(product);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.delete(productId);
    }

    @PostMapping("/update/{productId}")
    public void update(@PathVariable Long productId, @RequestBody ProductTO product) {
        productService.update(productId, product);
    }

}
