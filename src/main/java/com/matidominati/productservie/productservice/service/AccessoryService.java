package com.matidominati.productservie.productservice.service;

import com.matidominati.productservie.productservice.mapper.AccessoryTOMapper;
import com.matidominati.productservie.productservice.model.dto.AccessoryTO;
import com.matidominati.productservie.productservice.model.entity.AccessoryEntity;
import com.matidominati.productservie.productservice.repository.AccessoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.matidominati.productservie.productservice.service.helper.ServiceHelper.findByIdOrThrow;
import static com.matidominati.productservie.productservice.service.helper.ServiceHelper.updateAccessory;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccessoryService {
    private final AccessoryRepository accessoryRepository;
    private final AccessoryTOMapper mapper;

    public List<AccessoryTO> getAll() {
        log.info("Search process for all accessories has started");
        List<AccessoryTO> accessories = accessoryRepository.findAll().stream()
                .map(mapper::map)
                .toList();
        log.info("{} accessories found", accessories.size());
        return accessories;
    }

    public AccessoryTO getById(Long id) {
        log.info("Search process for accessory with ID: {} has started", id);
        AccessoryEntity accessory = findByIdOrThrow(id, accessoryRepository, AccessoryEntity.class);
        log.info("Accessory with ID: {} found", id);
        return mapper.map(accessory);
    }

    @Transactional
    public AccessoryTO create(AccessoryEntity accessory) {
        AccessoryEntity newAccessory = AccessoryEntity.create(accessory);
        accessoryRepository.save(newAccessory);
        return mapper.map(newAccessory);
    }

    @Transactional
    public void delete(Long id) {
        AccessoryEntity accessoryToDelete = findByIdOrThrow(id, accessoryRepository, AccessoryEntity.class);
        accessoryRepository.delete(accessoryToDelete);
        log.info("{} with ID: {} has been deleted.", accessoryToDelete.getAccessoryName(), accessoryToDelete.getAccessoryId());
    }

    @Transactional
    public AccessoryTO update(Long id, AccessoryTO updatedAccessory) {
        AccessoryEntity accessory = findByIdOrThrow(id, accessoryRepository, AccessoryEntity.class);
        log.info("Updating accessory with ID: {}", id);
        updateAccessory(accessory, updatedAccessory.getAccessoryName(), updatedAccessory.getAccessoryType(), updatedAccessory.getAccessoryPrice());
        accessoryRepository.save(accessory);
        log.info("Product data has been updated.");
        return mapper.map(accessory);
    }
}
