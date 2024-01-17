package com.matidominati.productservie.productservice.repository;

import com.matidominati.productservie.productservice.model.entity.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, Long> {

}
