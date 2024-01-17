package com.matidominati.productservie.productservice.service;

import com.matidominati.productservie.productservice.mapper.ConfigurationTOMapper;
import com.matidominati.productservie.productservice.repository.ConfigurationRepository;
import com.matidominati.productservie.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConfigurationService {

    private final ConfigurationRepository configurationRepository;
    private final ConfigurationTOMapper mapper;
    private final ProductRepository productRepository;

}
