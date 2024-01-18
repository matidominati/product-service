package com.matidominati.productservie.productservice.repository;

import com.matidominati.productservie.productservice.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByProductType(String productType);
}
