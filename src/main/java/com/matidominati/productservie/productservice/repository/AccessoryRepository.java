package com.matidominati.productservie.productservice.repository;

import com.matidominati.productservie.productservice.model.entity.AccessoryEntity;
import com.matidominati.productservie.productservice.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessoryRepository extends JpaRepository<AccessoryEntity, Long> {
    Optional<AccessoryEntity> findByAccessoryType(String accessoryType);

}
