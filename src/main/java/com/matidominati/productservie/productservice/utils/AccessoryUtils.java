package com.matidominati.productservie.productservice.utils;

import com.matidominati.productservie.productservice.model.entity.AccessoryEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccessoryUtils {

    public static void updateAccessory(AccessoryEntity accessory, String accessoryName, String accessoryType, BigDecimal accessoryPrice) {
        accessory.setAccessoryName(accessoryName);
        accessory.setAccessoryType(accessoryType);
        accessory.setAccessoryPrice(accessoryPrice);
    }
}
