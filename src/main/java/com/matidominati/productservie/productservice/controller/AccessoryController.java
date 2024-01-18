package com.matidominati.productservie.productservice.controller;

import com.matidominati.productservie.productservice.model.dto.AccessoryDTO;
import com.matidominati.productservie.productservice.model.entity.AccessoryEntity;
import com.matidominati.productservie.productservice.service.AccessoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accessories")
public class AccessoryController {

    private final AccessoryService accessoryService;

    @GetMapping("/{accessoryId}")
    public AccessoryDTO getById(@PathVariable Long accessoryId) {
        return accessoryService.getById(accessoryId);
    }

    @GetMapping
    public List<AccessoryDTO> getAll(@RequestParam(required = false) String type) {
        if (StringUtils.hasText(type)) {
            return accessoryService.getByType(type);
        }
        return accessoryService.getAll();
    }

    @GetMapping("/types")
    public List<String> getAvailableTypes() {
        return accessoryService.getAvailableTypes();
    }

    @PostMapping
    public AccessoryDTO create(@Valid @RequestBody AccessoryEntity accessory) {
        return accessoryService.create(accessory);
    }

    @PutMapping("/{accessoryId}")
    public AccessoryDTO update(@PathVariable Long accessoryId, @Valid @RequestBody AccessoryDTO accessory) {
        return accessoryService.update(accessoryId, accessory);
    }

    @DeleteMapping("/{accessoryId}")
    public void delete(@PathVariable Long accessoryId) {
        accessoryService.delete(accessoryId);
    }
}
