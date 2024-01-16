package com.matidominati.productservie.productservice.service;

import com.matidominati.productservie.productservice.mapper.ProductConfigurationTOMapper;
import com.matidominati.productservie.productservice.repository.ProductConfigurationRepository;
import com.matidominati.productservie.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductConfigurationService {

    private final ProductConfigurationRepository configurationRepository;
    private final ProductConfigurationTOMapper mapper;
    private final ProductRepository productRepository;

}
